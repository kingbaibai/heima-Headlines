package com.heima.common.exception;

import com.heima.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangqin
 * @Date: 2023/3/26 - 03 - 26 - 12:59
 * @Description: com.heima.common.exception
 * @version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHanler {

    @ExceptionHandler(Exception.class)
    public ResponseResult handlerException(Exception e) {
        return ResponseResult.errorResult(500,e.getMessage());
    }

    @ExceptionHandler(LeadnewsException.class)
    public ResponseResult handlerLeadnewsException(LeadnewsException e) {
        return new ResponseResult(e.getStatus(),e.getMessage());
    }
}
