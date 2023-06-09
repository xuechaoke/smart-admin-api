package net.lab1024.sa.admin.module.business.file.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import net.lab1024.sa.admin.module.business.file.dao.FileDao;
import net.lab1024.sa.admin.module.business.file.domain.entity.FileEntity;
import net.lab1024.sa.admin.module.business.file.domain.form.FileQueryForm;
import net.lab1024.sa.admin.module.business.file.domain.form.FileUploadForm;
import net.lab1024.sa.admin.module.business.file.domain.vo.FileDownloadVO;
import net.lab1024.sa.admin.module.business.file.domain.vo.FileMetadataVO;
import net.lab1024.sa.admin.module.business.file.domain.vo.FileUploadVO;
import net.lab1024.sa.admin.module.business.file.domain.vo.FileVO;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartPageUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FileService {

    /**
     * 文件名最大长度
     */
    private static final int FILE_NAME_MAX_LENGTH = 100;


    private final IFileStorageService fileStorageService;


    private final FileDao fileDao;


    public ResponseDTO<FileUploadVO> fileUpload(FileUploadForm uploadForm) {
        MultipartFile file = uploadForm.getFile();

        if (null == file || file.getSize() == 0) {
            return ResponseDTO.userErrorParam("上传文件不能为空");
        }
        // 校验文件名称
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            return ResponseDTO.userErrorParam("上传文件名称不能为空");
        }
        if (originalFilename.length() > FILE_NAME_MAX_LENGTH) {
            return ResponseDTO.userErrorParam("文件名称最大长度为：" + FILE_NAME_MAX_LENGTH);
        }
        // 校验文件大小
        String maxFileSize = "30MB";
        String maxSizeStr = maxFileSize.toLowerCase().replace("mb", "");
        long maxSize = Integer.parseInt(maxSizeStr) * 1024 * 1024L;
        if (file.getSize() > maxSize) {
            return ResponseDTO.userErrorParam("上传文件最大:" + maxSize);
        }
        // 获取文件服务
        ResponseDTO<FileUploadVO> response = fileStorageService.fileUpload(file);
        if (!response.getOk()) {
            return response;
        }

        // 上传成功 保存记录数据库
        FileUploadVO uploadVO = response.getData();

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(originalFilename);
        fileEntity.setFileSize(file.getSize());
        fileEntity.setFileKey(uploadVO.getFileKey());
        fileEntity.setCategoryId(1L);
        fileDao.insert(fileEntity);
        uploadVO.setFileId(fileEntity.getFileId());

        return response;
    }

    public List<FileVO> getFileList(List<String> fileKeyList) {
        if (CollectionUtils.isEmpty(fileKeyList)) {
            return Lists.newArrayList();
        }
        return fileKeyList.stream().map(this::getCacheFileVO).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private FileVO getCacheFileVO(String fileKey) {

        return fileDao.getByFileKey(fileKey);
    }

    /**
     * 根据文件绝对路径 获取文件URL
     * 支持单个 key 逗号分隔的形式
     *
     * @param fileKey
     * @return
     */
    public ResponseDTO<String> getFileUrl(String fileKey) {
        if (StringUtils.isBlank(fileKey)) {
            return ResponseDTO.error(UserErrorCode.PARAM_ERROR);
        }
        // 处理逗号分隔的字符串
        String keyList = StrUtil.split(fileKey, ",")
                .stream().map(this::getCacheUrl)
                .collect(Collectors.joining(","));
        return ResponseDTO.ok(keyList);
    }

    private String getCacheUrl(String fileKey) {
        ResponseDTO<String> responseDTO = fileStorageService.getFileUrl(fileKey);
        return responseDTO.getData();
    }

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<FileVO> queryPage(FileQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<FileVO> list = fileDao.queryPage(page, queryForm);
        return SmartPageUtil.convert2PageResult(page, list);
    }

    /**
     * 根据文件服务类型 和 FileKey 下载文件
     *
     * @param fileKey
     * @return
     * @throws IOException
     */
    public ResponseEntity<Object> downloadByFileKey(String fileKey,String userAgent) {
        FileVO fileVO = fileDao.getByFileKey(fileKey);
        if (fileVO == null) {
            HttpHeaders heads = new HttpHeaders();
            heads.add(HttpHeaders.CONTENT_TYPE, "text/html;charset=UTF-8");
            return new ResponseEntity<>("文件不存在：" + fileKey, heads, HttpStatus.OK);
        }

        // 根据文件服务类 获取对应文件服务 查询 url
        ResponseDTO<FileDownloadVO> responseDTO = fileStorageService.fileDownload(fileKey);
        if (!responseDTO.getOk()) {
            HttpHeaders heads = new HttpHeaders();
            heads.add(HttpHeaders.CONTENT_TYPE, "text/html;charset=UTF-8");
            return new ResponseEntity<>(responseDTO.getMsg() + "：" + fileKey, heads, HttpStatus.OK);
        }

        FileDownloadVO fileDownloadVO = responseDTO.getData();
        FileMetadataVO metadata = fileDownloadVO.getMetadata();

        // 设置下载头
        HttpHeaders heads = new HttpHeaders();
        heads.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(metadata.getFileSize()));
        heads.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream; charset=utf-8");
        heads.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileStorageService.getDownloadFileNameByUA(fileVO.getFileName(), userAgent));

        // 返回给前端
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(fileDownloadVO.getData(), heads, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 根据文件key 删除
     *
     * @param fileKey
     * @return
     */
    public ResponseDTO<String> deleteByFileKey(String fileKey) {
        if (StringUtils.isBlank(fileKey)) {
            return ResponseDTO.error(UserErrorCode.PARAM_ERROR);
        }
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileKey(fileKey);
        fileEntity = fileDao.selectOne(new QueryWrapper<>(fileEntity));
        if (null == fileEntity) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        // 根据文件服务类 获取对应文件服务 删除文件
        return fileStorageService.delete(fileKey);
    }


}
