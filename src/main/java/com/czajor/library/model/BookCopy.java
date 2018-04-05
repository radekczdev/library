package com.czajor.library.model;

import com.czajor.library.exceptions.WrongStatusException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Entity
@Table(name = "BOOKCOPIES")
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class BookCopy {

    private long id;
    private Book book;
    private Statuses status;

    public BookCopy(Book book, Statuses status) {
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

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    public Statuses getStatus() {
        return status;
    }


    public void setStatus(Statuses status) throws WrongStatusException {
        if(Arrays.stream(Statuses.values()).anyMatch(a -> a.equals(status))) {
            this.status = status;
        } else {
            throw new WrongStatusException();
        }
    }
}
