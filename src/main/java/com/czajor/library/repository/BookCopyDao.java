package com.czajor.library.repository;

import com.czajor.library.model.Book;
import com.czajor.library.model.BookCopy;
import com.czajor.library.model.Statuses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookCopyDao extends CrudRepository<BookCopy, Long> {
    @Override
    List<BookCopy> findAll();

    @Override
    Optional<BookCopy> findById(Long id);

    List<BookCopy> findAllByBook(Book book);

    List<BookCopy> findAllByBookAndStatus(Book book, Statuses status);
}
