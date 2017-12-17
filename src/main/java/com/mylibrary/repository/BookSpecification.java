package com.mylibrary.repository;

import com.mylibrary.dto.BookSearchCriteria;
import com.mylibrary.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookSpecification implements Specification<Book> {

    private static final String CONST_TITLE = "title";
    private static final String CONST_YEAR = "year";
    private static final String CONST_AUTHOR = "author";

    BookSearchCriteria criteria;

    public BookSpecification(BookSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        String title = criteria.getTitle().trim();
        if (Objects.nonNull(title) && !title.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get(CONST_TITLE)), "%" + title.toLowerCase() + "%"));
        }

        int year = criteria.getYear();
        if (Objects.nonNull(year) && year > 0) {
            predicates.add(cb.equal(root.<Integer>get(CONST_YEAR), year));
        }

        String author = criteria.getAuthor().trim();
        if (Objects.nonNull(author) && !author.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get(CONST_AUTHOR)), "%" + author.toLowerCase() + "%"));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
