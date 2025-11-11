package com.bite.book_demo.service;

import com.bite.book_demo.model.BookInfo;
import com.bite.book_demo.mapper.BookInfoMapper;
import com.bite.book_demo.model.PageRequest;
import com.bite.book_demo.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    public void addBook(BookInfo bookInfo) {
        bookInfoMapper.addBook(bookInfo);
    }

    public ResponseResult<BookInfo> getListByPage(PageRequest pageRequest) {
        // 1. 获得总的图书数
        // 2. 获取当前页的数据
        Integer count = bookInfoMapper.count();
        List<BookInfo> bookInfos = bookInfoMapper.selectBooksByPage(pageRequest);
        // 对结果进行二次处理
        for (BookInfo bookInfo : bookInfos) {
            if (bookInfo.getStatus() == 1) {
                bookInfo.setStatusCN("可借阅");
            } else {
                bookInfo.setStatusCN("不可借阅");
            }
        }
        return new ResponseResult<>(count, bookInfos, pageRequest);
    }

    public BookInfo queryBookById(Integer bookId) {
        return bookInfoMapper.queryBookById(bookId);
    }

    public void updateBook(BookInfo bookInfo) {
        bookInfoMapper.updateBook(bookInfo);
    }
}
