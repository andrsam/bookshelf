package com.mylibrary.repository;

import com.mylibrary.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAllByOrderById();

    @Query("select distinct b.year from Book b order by b.year")
    List<Integer> findDistinctYear();

    List<Book> findBookByYearOrderByTitle(int year);

    @Query("select distinct b.author from Book b order by b.author")
    List<String> findDistinctAuthorOrderByTitle();

    List<Book> findBookByAuthor(String author);
}
