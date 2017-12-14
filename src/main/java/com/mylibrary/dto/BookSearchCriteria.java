package com.mylibrary.dto;

import java.util.Objects;

public class BookSearchCriteria {
    private String title;

    private int year;

    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isReadyToSearch() {
        return Objects.nonNull(title) && Objects.nonNull(author) && Objects.nonNull(year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSearchCriteria that = (BookSearchCriteria) o;
        return year == that.year &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, author);
    }

    @Override
    public String toString() {
        return "BookSearchCriteria{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                '}';
    }
}
