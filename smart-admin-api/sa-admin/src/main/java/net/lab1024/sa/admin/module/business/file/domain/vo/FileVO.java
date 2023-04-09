package net.lab1024.sa.admin.module.business.file.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class FileVO {

    private Long fileId;

    private Integer folderType;

    private String fileName;

    private Integer fileSize;

    private String fileType;

    private String fileKey;

    private Long creatorId;

    private String creatorName;

    private Integer creatorUserType;

    private String fileUrl;

    private LocalDateTime createTime;
}
