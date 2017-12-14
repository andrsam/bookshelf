package com.mylibrary.dto;

import com.mylibrary.entity.Book;

import java.util.List;

public class BookDto {
    private long total;
    private List<Book> rows;

    public BookDto(long total, List<Book> bookList) {
        this.total = total;
        this.rows = bookList;
    }
}
