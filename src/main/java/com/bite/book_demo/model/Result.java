package com.bite.book_demo.model;

import com.bite.book_demo.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private ResultCodeEnum code;  // -1 未登录   -2 出错了    200 正常
    private String errMsg;
    private T data;
}
