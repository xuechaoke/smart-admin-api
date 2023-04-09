package net.lab1024.sa.admin.module.business.file.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "t_file")
public class FileEntity {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long fileId;



    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件key，用于文件下载
     */
    private String fileKey;

    private String desc;


    private Long categoryId;;


    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}

