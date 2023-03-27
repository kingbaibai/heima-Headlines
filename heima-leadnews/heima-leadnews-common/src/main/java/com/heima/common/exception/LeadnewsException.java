package com.heima.common.exception;

import com.heima.common.dtos.AppHttpCodeEnum;
import lombok.Getter;

/**
 * @author zhangqin
 * @Date: 2023/3/26 - 03 - 26 - 12:57
 * @Description: com.heima.common.exception
 * @version: 1.0
 */
@Getter
public class LeadnewsException extends RuntimeException{
    private Integer status;

    public LeadnewsException() {
        super();
    }

    public LeadnewsException(AppHttpCodeEnum appHttpCodeEnum) {
        super(appHttpCodeEnum.getErrorMessage());
        this.status = appHttpCodeEnum.getCode();
    }
}
