package com.czajor.library.controller;

import com.czajor.library.domain.BookDto;
import com.czajor.library.domain.ReaderDto;
import com.czajor.library.mapper.BookMapper;
import com.czajor.library.mapper.ReaderMapper;
import com.czajor.library.model.Book;
import com.czajor.library.model.Reader;
import com.czajor.library.service.BookCopyService;
import com.czajor.library.service.BookService;
import com.czajor.library.service.BorrowService;
import com.czajor.library.service.ReaderService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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

}
