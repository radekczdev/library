package com.czajor.library.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "BOOKS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "author", "year"})}
)
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class Book {
    private long id;
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public long getId() {
        return id;
    }

    @NotNull
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    @NotNull
    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    @NotNull
    @Column(name = "YEAR")
    public int getYear() {
        return year;
    }
}
