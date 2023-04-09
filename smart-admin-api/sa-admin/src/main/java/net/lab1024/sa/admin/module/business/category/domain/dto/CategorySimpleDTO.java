package net.lab1024.sa.admin.module.business.category.domain.dto;

import lombok.Data;


@Data
public class CategorySimpleDTO {

    private Long categoryId;

    private String categoryName;

    private String categoryFullName;

    private Long parentId;
}
