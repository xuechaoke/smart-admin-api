package net.lab1024.sa.admin.module.business.category.domain.vo;

import lombok.Data;

import java.util.List;


@Data
public class CategoryTreeVO {

    private Long categoryId;

    private String categoryName;

    private String categoryFullName;

    private Long parentId;

    private Long value;

    private String label;

    private List<CategoryTreeVO> children;
}
