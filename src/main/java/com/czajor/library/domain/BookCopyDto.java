package com.czajor.library.domain;

import com.czajor.library.model.Book;
import com.czajor.library.model.Borrow;
import com.czajor.library.model.Statuses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDto {
    private long id;
    private Book book;
    private Statuses status;
    private Borrow borrow;

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Statuses getStatus() {
        return status;
    }

    public Borrow getBorrow() {
        return borrow;
    }
}
