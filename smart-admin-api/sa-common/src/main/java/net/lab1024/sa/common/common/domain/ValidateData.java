package net.lab1024.sa.common.common.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ValidateData<T> {

    @NotNull(message = "数据不能为空哦")
    private T data;
}