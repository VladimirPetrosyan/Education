package main;

import java.util.Objects;

public final class Book {
    private final String title;
    private final String author;
    private final int year;
    private final int pages;

    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public String title() { return title; }
    public String author() { return author; }
    public int year() { return year; }
    public int pages() { return pages; }

    @Override public String toString() {
        return "Book{title='" + title + "', author='" + author + "', year=" + year + ", pages=" + pages + "}";
    }

    // Уникальность для distinct по (title, author, year)
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book b)) return false;
        return year == b.year &&
                Objects.equals(title, b.title) &&
                Objects.equals(author, b.author);
    }
    @Override public int hashCode() {
        return Objects.hash(title, author, year);
    }
}
