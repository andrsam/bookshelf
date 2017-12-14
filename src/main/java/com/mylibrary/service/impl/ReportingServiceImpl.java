package com.mylibrary.service.impl;

import com.mylibrary.entity.Book;
import com.mylibrary.repository.BookRepository;
import com.mylibrary.service.IReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ReportingServiceImpl implements IReportingService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Map<Integer, List<Book>> generateMapByYear() {
        Map<Integer, List<Book>> mapByYear = new TreeMap<>();
        bookRepository.findDistinctYear().forEach(year -> mapByYear.put(year, bookRepository.findBookByYearOrderByTitle(year)));
        return mapByYear;
    }

    @Override
    public Map<String, List<Book>> generateMapByAuthor() {
        Map<String, List<Book>> mapByAuthor = new TreeMap<>();
        bookRepository.findDistinctAuthorOrderByTitle().forEach(author -> mapByAuthor.put(author, bookRepository.findBookByAuthor(author)));
        return mapByAuthor;
    }
}
