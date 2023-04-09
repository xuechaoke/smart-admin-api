package net.lab1024.sa.admin.module.business.category.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CategoryVO {

    private String categoryName;

    private Integer categoryType;

    private Long parentId;

    private Integer sort;

    private String remark;

    private Boolean disabledFlag;

    private Long categoryId;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
