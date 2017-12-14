package com.mylibrary.repository;

import com.mylibrary.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookPagingRepository extends PagingAndSortingRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Override
    List<Book> findAll(Specification<Book> spec);

    List<Book> findAllByOrderById(Pageable pageable);
}
