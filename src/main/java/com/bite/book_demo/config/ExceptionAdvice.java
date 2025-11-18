package com.bite.book_demo.config;

import com.bite.book_demo.enums.ResultCodeEnum;
import com.bite.book_demo.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    //    @ExceptionHandler
//    public Result handler(Exception e){
//        log.error("发生异常, e:", e);
//        return Result.fail("内部错误, 请联系管理员");
//    }
//
//    @ExceptionHandler
//    public Result handler(NullPointerException e){
//        log.error("发生异常, e:", e);
//        return Result.fail("发生空指针异常, 请联系管理员");
//    }
//
//    @ExceptionHandler
//    public Result handler(IndexOutOfBoundsException e){
//        log.error("发生异常, e:", e);
//        return Result.fail("数组越界异常, 请联系管理员");
//    }

    @ExceptionHandler(Exception.class)
    public Result handler(Exception e){
        log.error("发生异常, e:", e);
        return new Result<>(ResultCodeEnum.FAIL, "内部错误, 请联系管理员", "");
    }

    @ExceptionHandler(NullPointerException.class)
    public Result handler2(Exception e){
        log.error("发生异常, e:", e);
        return new Result<>(ResultCodeEnum.FAIL, "发生空指针异常, 请联系管理员", "");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result handler3(Exception e){
        log.error("发生异常, e:", e);
        return new Result<>(ResultCodeEnum.FAIL, "数组越界异常, 请联系管理员", "");
    }
}
