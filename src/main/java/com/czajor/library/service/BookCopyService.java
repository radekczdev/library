package com.czajor.library.service;

import com.czajor.library.exceptions.BookCopyDoesntExist;
import com.czajor.library.exceptions.WrongStatusException;
import com.czajor.library.model.Book;
import com.czajor.library.model.BookCopy;
import com.czajor.library.model.Statuses;
import com.czajor.library.repository.BookCopyDao;
import com.czajor.library.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookCopyService {
    @Autowired
    private BookCopyDao bookCopyDao;

    @Autowired
    private BookDao bookDao;

    public void addBookCopy(long bookId) {
        Book book = bookDao.findById(bookId).get();
        bookCopyDao.save(new BookCopy(book, Statuses.ACTIVE));
    }

    public void changeBookCopyStatus(Long id, String statusDto) throws Exception {
        Statuses status;
        if(Arrays.stream(Statuses.values()).anyMatch(a -> a.toString().equals(statusDto))) {
            status = Statuses.valueOf(statusDto);
        } else {
            throw new WrongStatusException();
        }
        BookCopy bookCopy = bookCopyDao.findById(id).orElseThrow(BookCopyDoesntExist::new);
        bookCopy.setStatus(status);
        bookCopyDao.save(bookCopy);
    }

    public List<BookCopy> checkAvailableBookCopies(long bookId) {
        Book book = bookDao.findById(bookId).get();
        return bookCopyDao.findAllByBookAndStatus(book, Statuses.ACTIVE);
    }

    public int checkAvailableBookCopiesAmount(long bookId) throws BookCopyDoesntExist {
        Book book = bookDao.findById(bookId).orElseThrow(BookCopyDoesntExist::new);
        return bookCopyDao.findAllByBookAndStatus(book, Statuses.ACTIVE).size();
    }
}
