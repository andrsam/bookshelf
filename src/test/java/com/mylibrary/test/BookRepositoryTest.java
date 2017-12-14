package com.mylibrary.test;

import com.mylibrary.config.PersistenceConfig;
import com.mylibrary.entity.Book;
import com.mylibrary.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    private Book book = new Book();

    @Before
    public void setUp() throws Exception {
        book.setTitle("Test title");
        book.setAuthor("Test author");
        book.setYear(2017);
        bookRepository.save(book);
    }

    @Test
    @Transactional
    @Rollback
    public void testAddBook() {
        Book testBook = bookRepository.findBookByAuthor("Test author").get(0);
        assertEquals(testBook, book);
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateBook() {
        book.setYear(1900);
        bookRepository.save(book);
        Book testBook = bookRepository.findOne(book.getId());
        assertEquals(testBook.getYear(), 1900);
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteBook() {
        bookRepository.save(book);
        bookRepository.delete(book.getId());
        assertNull(bookRepository.findOne(book.getId()));
    }
}