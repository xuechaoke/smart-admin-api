package net.lab1024.sa.admin.module.business.category.domain.form;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CategoryUpdateForm extends CategoryAddForm {

    @NotNull(message = "类目id不能为空")
    private Long categoryId;
}
