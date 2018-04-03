package com.czajor.library.domain;

import com.czajor.library.model.Book;
import com.czajor.library.model.Borrow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookCopyDto {
    private long id;
    private Book book;
    private String status;
    private Borrow borrow;
}
