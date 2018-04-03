package com.czajor.library.repository;

import com.czajor.library.model.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ReaderDao extends CrudRepository<Reader, Long> {
    @Override
    List<Reader> findAll();

    List<Reader> findBySurname(String surname);
}
