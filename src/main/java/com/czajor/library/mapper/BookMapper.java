package com.czajor.library.mapper;

import com.czajor.library.domain.BookDto;
import com.czajor.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book mapToBook(BookDto bookDto) {
        return new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYear()
        );
    }
}
