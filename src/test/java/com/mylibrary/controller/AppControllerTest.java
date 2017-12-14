package com.mylibrary.controller;

import com.mylibrary.entity.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

public class AppControllerTest {

    private Book book = new Book();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<Book> httpEntity;
    private RestTemplate restTemplate = new RestTemplate();
    private String applicationUrl;
    private Properties properties = new Properties();
    private String saveUrl;
    private String deleteUrl;

    @Before
    public void setUp() throws Exception {
        book.setTitle("Test title");
        book.setAuthor("Test author");
        book.setYear(2017);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpEntity = new HttpEntity<>(book, headers);
        properties.load(getClass().getClassLoader().getResourceAsStream("tests.properties"));
        applicationUrl = properties.getProperty("applicationUrl");
        saveUrl = applicationUrl + "/saveBook";
        deleteUrl = applicationUrl + "/deleteBook";
    }


    @Test
    @Ignore
    public void saveBook() throws Exception {

        Book savedBook = restTemplate.exchange
                (saveUrl,
                        HttpMethod.POST,
                        httpEntity,
                        Book.class)
                .getBody();
        Assert.assertNotNull(savedBook);
    }

    @Test
    @Ignore
    public void deleteBook() throws Exception {
        Book savedBook = restTemplate.exchange
                (saveUrl,
                        HttpMethod.POST,
                        httpEntity,
                        Book.class)
                .getBody();
        Assert.assertNotNull(savedBook);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", String.valueOf(savedBook.getId()));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String response = restTemplate.postForEntity(deleteUrl, request, String.class).getBody();
        Assert.assertEquals(response, "{\"success\":true}");
    }

    @Test
    public void listBooksJson() {

    }
}