package com.mylibrary.controller;

import com.mylibrary.dto.BookDto;
import com.mylibrary.dto.BookSearchCriteria;
import com.mylibrary.entity.Book;
import com.mylibrary.entity.BookSpecification;
import com.mylibrary.repository.BookPagingRepository;
import com.mylibrary.repository.BookRepository;
import com.mylibrary.service.IReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    private BookPagingRepository bookPagingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IReportingService reportingService;

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @GetMapping(value = "/api/books", produces = "application/json;UTF-8")
    @ResponseBody
    public BookDto listBooksJson(@ModelAttribute BookSearchCriteria searchCriteria, @RequestParam("page") int page, @RequestParam("rows") int rows) {
        long count = bookRepository.count();
        List<Book> books = bookPagingRepository.findAllByOrderById(new PageRequest(page, rows));

        if (searchCriteria.isReadyToSearch()) {
            BookSpecification specification = new BookSpecification(searchCriteria);
            books = bookPagingRepository.findAll(specification);
            count = books.size();
        }
        return new BookDto(count, books);
    }

    @PostMapping(value = "saveBook", produces = "application/json;UTF-8")
    @ResponseBody
    public Book saveBook(@ModelAttribute Book book) {
        return bookRepository.save(book);
    }

    @PostMapping(value = "deleteBook", produces = "application/json;UTF-8")
    @ResponseBody
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.delete(id);
        return "{\"success\":true}";
    }

    @GetMapping("report")
    public ModelAndView createReport(@RequestParam("groupby") String groupBy) {
        Map<Integer, List<Book>> mapByYear = reportingService.generateMapByYear();
        Map<String, List<Book>> mapByAuthor = reportingService.generateMapByAuthor();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("booksMap", groupBy.equals("year") ? mapByYear : mapByAuthor);
        modelAndView.addObject("groupBy", groupBy);
        return modelAndView;
    }
}
