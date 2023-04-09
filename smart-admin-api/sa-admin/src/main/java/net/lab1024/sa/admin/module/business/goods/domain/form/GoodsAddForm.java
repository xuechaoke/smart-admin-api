package net.lab1024.sa.admin.module.business.goods.domain.form;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品 添加表单
 *
 * @Author 1024创新实验室: 胡克
 * @Date 2021-10-25 20:26:54
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ），2012-2022
 */
@Data
public class GoodsAddForm {

    @NotNull(message = "商品分类不能为空")
    private Long categoryId;

    @NotBlank(message = "商品名称不能为空")
    private String goodsName;

    private Integer goodsStatus;

    @NotBlank(message = "产地 不能为空 ")
    private String place;

    @NotNull(message = "商品价格不能为空")
    @DecimalMin(value = "0", message = "商品价格最低0")
    private BigDecimal price;

    @NotNull(message = "上架状态不能为空")
    private Boolean shelvesFlag;

    private String remark;
}
