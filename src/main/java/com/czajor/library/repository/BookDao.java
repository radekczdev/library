package com.czajor.library.repository;

import com.czajor.library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface BookDao extends CrudRepository<Book, Long> {
    @Override
    List<Book> findAll();

    Book findByTitle(String title);
}
