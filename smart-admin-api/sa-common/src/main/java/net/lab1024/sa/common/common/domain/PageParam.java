package net.lab1024.sa.common.common.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 分页基础参数
 *
 */
@Data
public class PageParam {

    private Integer pageNum;

    @NotNull(message = "每页数量不能为空")
    @Max(value = 200, message = "每页最大为200")
    private Integer pageSize;

    protected Boolean searchCount;

    @Size(max = 10, message = "排序字段最多10")
    @Valid
    private List<SortItem> sortItemList;

    /**
     * 排序DTO类
     */
    @Data
    public static class SortItem {

        @NotNull(message = "排序规则不能为空")
        private Boolean isAsc;

        @NotBlank(message = "排序字段不能为空")
        @Length(max = 30, message = "排序字段最多30")
        private String column;
    }
}
