package com.czajor.library.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class ReaderDto {
    private long id;
    private String name;
    private String surname;
    private LocalDateTime estDate;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDateTime getEstDate() {
        return estDate;
    }
}
