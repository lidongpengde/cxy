package com.cxy.service.impl;

import com.cxy.dao.BookDao;
import com.cxy.entity.Book;
import com.cxy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lidp on 2017/3/19.
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDao bookDao;
    public Book getBookById(long bookId) {
        Book book=bookDao.queryById(bookId);
        return book;
    }
}
