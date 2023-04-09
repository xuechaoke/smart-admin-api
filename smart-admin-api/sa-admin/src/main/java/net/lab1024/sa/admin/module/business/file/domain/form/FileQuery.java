package net.lab1024.sa.admin.module.business.file.domain.form;

import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;
import org.springframework.web.multipart.MultipartFile;


@Data
public class FileQuery extends PageParam {


    private Integer categoryId;


    private String title;

    private String url;









}
