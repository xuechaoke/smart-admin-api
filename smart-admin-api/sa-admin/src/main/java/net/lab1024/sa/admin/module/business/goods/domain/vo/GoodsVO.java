package net.lab1024.sa.admin.module.business.goods.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品
 *
 * @Author 1024创新实验室: 胡克
 * @Date 2021-10-25 20:26:54
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ），2012-2022
 */
@Data
public class GoodsVO  {

    private Long categoryId;

    private String goodsName;

    private Integer goodsStatus;

    private String place;

    private BigDecimal price;

    private Boolean shelvesFlag;

    private String remark;

    private Long goodsId;

    private String categoryName;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
