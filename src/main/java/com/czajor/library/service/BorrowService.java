package com.czajor.library.service;

import com.czajor.library.exceptions.BorrowNotFound;
import com.czajor.library.exceptions.ReaderNotFound;
import com.czajor.library.model.BookCopy;
import com.czajor.library.model.Borrow;
import com.czajor.library.model.Reader;
import com.czajor.library.model.Statuses;
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
    private BookCopyService bookCopyService;

    public void borrowBook(long bookId, long readerId) throws Exception {
        BookCopy bookCopy = bookCopyService.checkAvailableBookCopies(bookId).iterator().next();
        bookCopyService.changeBookCopyStatus(bookCopy.getId(), Statuses.BORROWED.toString());
        Reader reader = readerDao.findById(readerId).orElseThrow(ReaderNotFound::new);
        borrowDao.save(new Borrow(bookCopy, reader));
    }

    public void returnBook(long borrowId) throws Exception {
        Borrow borrow = borrowDao.findById(borrowId).orElseThrow(BorrowNotFound::new);
        BookCopy bookCopy = borrow.getBookCopy();
        bookCopyService.changeBookCopyStatus(bookCopy.getId(), Statuses.ACTIVE.toString());
        borrow.returnBook();
        borrowDao.save(borrow);
    }
}
