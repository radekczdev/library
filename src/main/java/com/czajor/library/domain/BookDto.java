package com.czajor.library.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class BookDto {
    private long id;
    private String title;
    private String author;
    private int year;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}
