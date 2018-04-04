package com.czajor.library.service;

import com.czajor.library.exceptions.BorrowNotFound;
import com.czajor.library.exceptions.ReaderNotFound;
import com.czajor.library.model.Book;
import com.czajor.library.model.BookCopy;
import com.czajor.library.model.Borrow;
import com.czajor.library.model.Reader;
import com.czajor.library.repository.BookCopyDao;
import com.czajor.library.repository.BorrowDao;
import com.czajor.library.repository.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {
    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private BookCopyDao bookCopyDao;

    @Autowired
    private BookCopyService bookCopyService;

    public void borrowBook(Book book, long readerId) throws ReaderNotFound {
        BookCopy bookCopy = bookCopyService.checkAvailableBookCopies(book).get(0);
        bookCopy.setStatus(BookCopy.BORROWED);
        bookCopyDao.save(bookCopy);
        Reader reader = readerDao.findById(readerId).orElseThrow(ReaderNotFound::new);
        borrowDao.save(new Borrow(bookCopy, reader));
    }

    public void returnBook(Borrow borrow) throws BorrowNotFound {
        BookCopy bookCopy = borrow.getBookCopy();
        bookCopy.setStatus(BookCopy.ACTIVE);
        bookCopyDao.save(bookCopy);
        Borrow borrowDb = borrowDao.findById(borrow.getId()).orElseThrow(BorrowNotFound::new);
        borrowDb.returnBook();
        borrowDao.save(borrowDb);
    }
}
