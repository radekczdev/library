package com.czajor.library.mapper;

import com.czajor.library.domain.BookCopyDto;
import com.czajor.library.model.BookCopy;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {
    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getBook(),
                bookCopyDto.getStatus()
        );
    }
}
