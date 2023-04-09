package net.lab1024.sa.admin.module.business.category.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class CategoryBaseDTO {

    @NotBlank(message = "类目名称不能为空")
    @Length(max = 20, message = "类目名称最多20字符")
    private String categoryName;

    private Integer categoryType;

    private Long parentId;

    private Integer sort;

    @Length(max = 200, message = "备注最多200字符")
    private String remark;

    @NotNull(message = "禁用状态不能为空")
    private Boolean disabledFlag;
}
