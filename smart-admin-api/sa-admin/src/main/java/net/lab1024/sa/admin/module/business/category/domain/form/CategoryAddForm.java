package net.lab1024.sa.admin.module.business.category.domain.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Data
public class CategoryAddForm {

    @NotBlank(message = "类目名称不能为空")
    @Length(max = 20, message = "类目名称最多20字符")
    private String categoryName;


    private Long parentId;

    private Integer sort;


}
