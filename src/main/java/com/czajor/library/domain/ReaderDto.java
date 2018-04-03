package com.czajor.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReaderDto {
    private long id;
    private String name;
    private String surname;
    private LocalDateTime estDate;
}
