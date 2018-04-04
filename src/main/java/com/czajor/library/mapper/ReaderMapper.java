package com.czajor.library.mapper;

import com.czajor.library.domain.ReaderDto;
import com.czajor.library.model.Reader;
import org.springframework.stereotype.Component;

@Component
public class ReaderMapper {
    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(
                readerDto.getName(),
                readerDto.getSurname()
        );
    }
}
