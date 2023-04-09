package net.lab1024.sa.admin.module.business.file.domain.vo;

import lombok.Data;

@Data
public class FileDownloadVO {

    /**
     * 文件字节数据
     */
    private byte[] data;

    /**
     * 文件元数据
     */
    private FileMetadataVO metadata;


}
