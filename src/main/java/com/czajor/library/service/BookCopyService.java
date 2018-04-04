package com.czajor.library.service;

import com.czajor.library.exceptions.BookCopyDoesntExist;
import com.czajor.library.model.Book;
import com.czajor.library.model.BookCopy;
import com.czajor.library.repository.BookCopyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyService {
    @Autowired
    private BookCopyDao bookCopyDao;

    public void addBookCopy(BookCopy bookCopy) {
        bookCopyDao.save(bookCopy);
    }

    public void changeBookCopyStatus(Long id, String status) throws BookCopyDoesntExist{
        BookCopy bookCopy = bookCopyDao.findById(id).orElseThrow(BookCopyDoesntExist::new);
        bookCopy.setStatus(status);
        bookCopyDao.save(bookCopy);
    }

    public List<BookCopy> checkAvailableBookCopies(Book book) {
        return bookCopyDao.findAllByBookAndStatus(book, BookCopy.ACTIVE);
    }
}
