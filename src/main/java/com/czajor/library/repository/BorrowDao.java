package com.czajor.library.repository;

import com.czajor.library.model.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BorrowDao extends CrudRepository<Borrow, Long> {
    @Override
    List<Borrow> findAll();
}
