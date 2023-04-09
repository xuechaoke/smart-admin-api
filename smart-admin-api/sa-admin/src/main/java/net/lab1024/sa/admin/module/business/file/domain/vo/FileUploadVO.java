package net.lab1024.sa.admin.module.business.file.domain.vo;

import lombok.Data;

@Data
public class FileUploadVO {

    private Long fileId;

    private String fileName;

    private String fileUrl;

    private String fileKey;

    private Long fileSize;

    private String fileType;
}
