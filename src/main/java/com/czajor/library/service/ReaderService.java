package com.czajor.library.service;

import com.czajor.library.model.Reader;
import com.czajor.library.repository.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {
    @Autowired
    ReaderDao readerDao;

    public void addReader(Reader reader) {
        readerDao.save(reader);
    }
}
