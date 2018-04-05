package com.czajor.library.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "BORROWED")
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class Borrow {
    private long id;
    private BookCopy bookCopy;
    private Reader reader;
    private LocalDateTime borrowed;
    private LocalDateTime returned;

    public Borrow(BookCopy bookCopy, Reader reader) {
        this.bookCopy = bookCopy;
        this.reader = reader;
        this.borrowed = LocalDateTime.now();
    }

    public void returnBook() {
        returned = LocalDateTime.now();
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public long getId() {
        return id;
    }


    @NotNull
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(name = "BOOK_COPY_ID")
    public BookCopy getBookCopy() {
        return bookCopy;
    }

    @NotNull
    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "READER_ID")
    public Reader getReader() {
        return reader;
    }

    @NotNull
    @Column(name = "BORROWED_DATE")
    public LocalDateTime getBorrowed() {
        return borrowed;
    }

    @Column(name = "RETURNED_DATE")
    public LocalDateTime getReturned() {
        return returned;
    }
}
