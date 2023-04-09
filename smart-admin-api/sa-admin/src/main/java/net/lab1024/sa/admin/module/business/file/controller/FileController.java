package net.lab1024.sa.admin.module.business.file.controller;

import cn.hutool.extra.servlet.ServletUtil;
import net.lab1024.sa.admin.module.business.file.domain.form.FileUploadForm;
import net.lab1024.sa.admin.module.business.file.domain.vo.FileUploadVO;
import net.lab1024.sa.admin.module.business.file.service.FileService;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping("/file/upload")
    public ResponseDTO<FileUploadVO> upload( FileUploadForm file) {
        return fileService.fileUpload(file);
    }



    @GetMapping("/file/getFileUrl")
    public ResponseDTO<String> getUrl(@RequestParam String fileKey) {
        return fileService.getFileUrl(fileKey);
    }

    @GetMapping("/file/downLoad")
    public ResponseEntity<Object> downLoad(@RequestParam String fileKey, HttpServletRequest request) {
        String userAgent = ServletUtil.getHeaderIgnoreCase(request, "USER-AGENT");
        return fileService.downloadByFileKey(fileKey, userAgent);
    }


}
