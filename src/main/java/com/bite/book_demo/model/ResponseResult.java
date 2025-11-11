package com.bite.book_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResult<T> {
    private Integer total;
    private List<T> records;
    private PageRequest pageRequest;
}
