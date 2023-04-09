package net.lab1024.sa.admin.module.business.category.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum CategoryTypeEnum {

    /**
     * 1 商品
     */
    GOODS(1, "商品"),

    /**
     * 2 自定义
     */
    CUSTOM(2, "自定义"),

    ;

    private final Integer value;

    private final String desc;
}
