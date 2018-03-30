package com.czajor.library.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "READERS")
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class Reader {
    private long id;
    private String name;
    private String surname;
    private LocalDateTime estDate;

    public Reader(String name, String surname, Date estDate) {
        this.name = name;
        this.surname = surname;
        this.estDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public long getId() {
        return id;
    }

    @NotNull
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @NotNull
    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    @NotNull
    @Column(name = "EST_DATE")
    public LocalDateTime getEstDate() {
        return estDate;
    }

}
