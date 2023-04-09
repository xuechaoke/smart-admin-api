package net.lab1024.sa.common.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum UnexpectedErrorCode implements ErrorCode {

    BUSINESS_HANDING(20001, "呃~ 业务繁忙，请稍后重试"),
    PAY_ORDER_ID_ERROR(20002, "付款单id发生了异常，请联系技术人员排查"),

    ;

    private final int code;

    private final String msg;

    private final String level;

    UnexpectedErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.level = LEVEL_UNEXPECTED;
    }

}
