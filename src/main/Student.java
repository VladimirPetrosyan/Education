package main;

import java.util.List;

public final class Student {
    private final String name;
    private final List<Book> books;

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = List.copyOf(books); // защитная копия (иммутабельная)
    }

    public String name() { return name; }
    public List<Book> getBooks() { return books; }

    @Override public String toString() {
        return "Student{name='" + name + "', books=" + books.size() + "}";
    }
}

