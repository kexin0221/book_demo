package com.bite.book_demo.controller;

import com.bite.book_demo.constant.Constants;
import com.bite.book_demo.enums.BookStatusEnum;
import com.bite.book_demo.enums.ResultCodeEnum;
import com.bite.book_demo.model.*;
import com.bite.book_demo.service.BookService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/addBook")
    public String addBook(BookInfo bookInfo) {
        log.info("添加图书, request: {}", bookInfo);
        //1. 参数校验
        if (!StringUtils.hasLength(bookInfo.getBookName())) {
            log.error("添加图书，参数不合法，request: {}", bookInfo);
            return "书名不能为空！";
        }
        if (!StringUtils.hasLength(bookInfo.getAuthor())) {
            log.error("添加图书，参数不合法，request: {}", bookInfo);
            return "作者不能为空！";
        }
        if (bookInfo.getCount() == null) {
            log.error("添加图书，参数不合法，request: {}", bookInfo);
            return "数量不能为空！";
        }
        if (bookInfo.getPrice() == null) {
            log.error("添加图书，参数不合法，request: {}", bookInfo);
            return "价格不能为空！";
        }
        if (!StringUtils.hasLength(bookInfo.getPublish())) {
            log.error("添加图书，参数不合法，request: {}", bookInfo);
            return "出版社不能为空！";
        }
        if (bookInfo.getStatus() == null) {
            log.error("添加图书，参数不合法，request: {}", bookInfo);
            return "状态码不能为空！";
        }
        //2. 存储数据
        //3. 返回结果
        try {
            bookService.addBook(bookInfo);
            return "";
        } catch (Exception e) {
            log.error("添加图书异常, e: {}", String.valueOf(e));
            return "添加图书发生异常！";
        }
    }

    @RequestMapping("/getListByPage")
    public Result<ResponseResult<BookInfo>> getListByPage(PageRequest pageRequest, HttpSession session) {
        if (session.getAttribute(Constants.SESSION_USER_KEY) == null) {
            return new Result<>(ResultCodeEnum.UNLOGIN, "用户未登录", null);
        }
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER_KEY);
        if (userInfo == null || userInfo.getId() <= 0) {
            // 用户未登录
            return new Result<>(ResultCodeEnum.UNLOGIN, "用户未登录", null);
        }
        return new Result<>(ResultCodeEnum.SUCCESS, "", bookService.getListByPage(pageRequest));
    }

    @RequestMapping("/queryBookById")
    public BookInfo queryBookById(Integer bookId) {
        log.info("查询图书信息，bookId: {}", bookId);
        return bookService.queryBookById(bookId);
    }

    @RequestMapping("/updateBook")
    public String updateBook(BookInfo bookInfo) {
        log.info("修改图书, bookInfo: {}", bookInfo);
        try {
            bookService.updateBook(bookInfo);
            return "";
        } catch (Exception e) {
            log.error("修改图书发生异常，e: ", e);
            return "修改图书发生异常...";
        }
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(Integer bookId) {
        log.info("删除图书, bookId: {}", bookId);
        try {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(bookId);
            bookInfo.setStatus(BookStatusEnum.DELETED.getCode());
            bookService.updateBook(bookInfo);
            return "";
        } catch (Exception e) {
            log.error("删除图书发生异常，e: ", e);
            return "删除图书发生异常...";
        }
    }

    @RequestMapping("/batchDelete")
    public Boolean batchDelete(@RequestParam List<Integer> ids) {
        log.info("批量删除图书, ids: {}", ids);
        try {
            bookService.batchDelete(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除图书失败, e: ", e);
            return false;
        }
    }
}
