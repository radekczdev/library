package com.czajor.library.domain;

import com.czajor.library.model.BookCopy;
import com.czajor.library.model.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowDto {
    private long id;
    private BookCopy bookCopy;
    private Reader reader;
    private LocalDateTime borrowed;
    private LocalDateTime returned;
}
