package com.czajor.library.service;

import com.czajor.library.model.Book;
import com.czajor.library.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void addBook(Book book) {
        bookDao.save(book);
    }
}
