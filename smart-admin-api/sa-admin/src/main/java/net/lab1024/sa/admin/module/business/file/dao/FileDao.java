package net.lab1024.sa.admin.module.business.file.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.business.file.domain.entity.FileEntity;
import net.lab1024.sa.admin.module.business.file.domain.form.FileQueryForm;
import net.lab1024.sa.admin.module.business.file.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface FileDao extends BaseMapper<FileEntity> {


    FileVO getByFileKey(@Param("fileKey") String fileKey);


    List<FileVO> queryPage(Page page, @Param("queryForm") FileQueryForm queryForm);

}
