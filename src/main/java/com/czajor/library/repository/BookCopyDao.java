package com.czajor.library.repository;

import com.czajor.library.model.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface BookCopyDao extends CrudRepository<BookCopy, Long> {
}
