package com.czajor.library.mapper;

import com.czajor.library.domain.BorrowDto;
import com.czajor.library.model.Borrow;
import org.springframework.stereotype.Component;

@Component
public class BorrowMapper {
    public Borrow mapToBorrow(BorrowDto borrowDto) {
        return new Borrow(
                borrowDto.getBookCopy(),
                borrowDto.getReader()
        );
    }
}
