package net.lab1024.sa.admin.module.business.file.domain.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class FileUploadForm {


    private String fileUrl;
    private Long categoryId;

    private MultipartFile file;






}
