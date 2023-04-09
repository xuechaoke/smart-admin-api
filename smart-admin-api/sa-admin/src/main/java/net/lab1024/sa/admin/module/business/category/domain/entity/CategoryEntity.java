package net.lab1024.sa.admin.module.business.category.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("t_category")
public class CategoryEntity {

    @TableId(type = IdType.AUTO)
    private Long categoryId;

    /**
     * 类目名称
     */
    private String categoryName;


    private Integer categoryUrl;

    /**
     * 父级类目id
     */
    private Long parentId;



    /**
     * 排序
     */
    private Integer sort;

}
