package com.czajor.library.controller;

import com.czajor.library.domain.BookDto;
import com.czajor.library.domain.ReaderDto;
import com.czajor.library.exceptions.BookCopyDoesntExist;
import com.czajor.library.mapper.BookMapper;
import com.czajor.library.mapper.BorrowMapper;
import com.czajor.library.mapper.ReaderMapper;
import com.czajor.library.service.BookCopyService;
import com.czajor.library.service.BookService;
import com.czajor.library.service.BorrowService;
import com.czajor.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/")
public class LibraryApi {
    @Autowired
    private BookCopyService bookCopyService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderMapper readerMapper;

    @RequestMapping(value = "reader", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        readerService.addReader(readerMapper.mapToReader(readerDto));
    }

    @RequestMapping(value = "book", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(value = "bookCopy", method = RequestMethod.POST)
    public void addBookCopy(@RequestParam long bookId) {
        bookCopyService.addBookCopy(bookId);
    }

    @RequestMapping(value = "bookCopy", method = RequestMethod.PUT)
    public void changeBookCopyStatus(@RequestParam Long bookCopyId, @RequestParam String status) {
        try {
            bookCopyService.changeBookCopyStatus(bookCopyId, status);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = "bookCopy", method = RequestMethod.GET)
    public int checkAvailableBookCopiesAmount(@RequestParam Long bookId) throws BookCopyDoesntExist {
        return bookCopyService.checkAvailableBookCopiesAmount(bookId);
    }

    @RequestMapping(value = "borrow", method = RequestMethod.POST)
    public void borrowBookCopy(@RequestParam long bookId, @RequestParam long readerId) throws Exception {
        borrowService.borrowBook(bookId, readerId);
    }

    @RequestMapping(value = "borrow", method = RequestMethod.PUT)
    public void returnBookCopy(@RequestParam long borrowId) throws Exception {
        borrowService.returnBook(borrowId);
    }
}
