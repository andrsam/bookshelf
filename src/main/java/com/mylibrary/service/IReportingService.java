package com.mylibrary.service;

import com.mylibrary.entity.Book;

import java.util.List;
import java.util.Map;

public interface IReportingService {
    Map<Integer, List<Book>> generateMapByYear();

    Map<String, List<Book>> generateMapByAuthor();
}
