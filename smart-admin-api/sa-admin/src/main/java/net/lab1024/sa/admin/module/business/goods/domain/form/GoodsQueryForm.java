package net.lab1024.sa.admin.module.business.goods.domain.form;

import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;
import org.hibernate.validator.constraints.Length;

/**
 * 商品 分页查询
 *
 * @Author 1024创新实验室: 胡克
 * @Date 2021-10-25 20:26:54
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ），2012-2022
 */
@Data
public class GoodsQueryForm extends PageParam {

    private Integer categoryId;

    @Length(max = 30, message = "搜索词最多30字符")
    private String searchWord;

    private Integer goodsStatus;

    private String place;

    private Boolean shelvesFlag;

    private Boolean deletedFlag;
}
