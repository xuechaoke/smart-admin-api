package net.lab1024.sa.admin.module.business.file.domain.vo;

import lombok.Data;


@Data
public class FileMetadataVO {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小/字节
     */
    private Long fileSize;

    /**
     * 文件格式
     */
    private String fileFormat;
}
