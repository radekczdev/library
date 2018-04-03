package com.czajor.library.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BOOKCOPIES")
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class BookCopy {
    public static String ACTIVE = "book ready to borrow";
    public static String BORROWED = "book is currently borrowed";
    public static String WORN_OUT = "book is worn out";
    public static String LOST = "book has been lost";

    private long id;
    private Book book;
    private String status;
    private Borrow borrow;

    public BookCopy(Book book, String status) {
        this.book = book;
        this.status = status;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public long getId() {
        return id;
    }

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    public Book getBook() {
        return book;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    @OneToOne(
            mappedBy = "bookCopy",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public Borrow getBorrow() {
        return borrow;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
