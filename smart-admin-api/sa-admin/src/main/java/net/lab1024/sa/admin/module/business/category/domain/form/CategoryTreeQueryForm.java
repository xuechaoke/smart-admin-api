package net.lab1024.sa.admin.module.business.category.domain.form;

import lombok.Data;


@Data
public class CategoryTreeQueryForm {

    private Integer categoryType;

    private Long parentId;
}
