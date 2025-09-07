package main;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Мини-тесты самописных структур
        checkMyHashSet();
        checkMyArrayList();

        System.out.println("\n=== Задание со стримом (одна цепочка без промежуточных переменных) ===");

        // ВАЖНО: один stream без промежуточных переменных (никаких локальных между операциями)
        List.of(
                        new Student("Дима", List.of(
                                new Book("Effective Java", "Joshua Bloch", 2018, 416),
                                new Book("Clean Code", "Robert C. Martin", 2008, 464),
                                new Book("Design Patterns", "GoF", 1994, 395),
                                new Book("Java Concurrency in Practice", "Goetz", 2006, 424),
                                new Book("Modern Java in Action", "Raoul-Gabriel Urma", 2019, 592)
                        )),
                        new Student("Ваня", List.of(
                                new Book("Kotlin in Action", "Dmitry Jemerov", 2017, 360),
                                new Book("Head First Design Patterns", "Freeman", 2004, 694),
                                new Book("Clean Architecture", "Robert C. Martin", 2017, 432),
                                new Book("Refactoring", "Martin Fowler", 1999, 448),
                                new Book("Domain-Driven Design", "Eric Evans", 2003, 560)
                        )),
                        new Student("Саша", List.of(
                                new Book("Spring in Action", "Craig Walls", 2018, 520),
                                new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002, 560),
                                new Book("Test-Driven Development", "Kent Beck", 2002, 240),
                                new Book("Clean Code", "Robert C. Martin", 2008, 464),
                                new Book("Effective Java", "Joshua Bloch", 2018, 416)
                        ))
                ).stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Book::pages))
                .distinct()
                .filter(b -> b.year() > 2000)
                .limit(3)
                .peek(b -> System.out.println("Год выпуска: " + b.year()))
                .findFirst()
                .map(Book::year)
                .ifPresentOrElse(
                        y -> System.out.println("Итог: найденная книга выпущена в " + y + " году."),
                        () -> System.out.println("Итог: подходящая книга не найдена.")
                );
    }

    private static void checkMyHashSet() {
        System.out.println("=== MyHashSet (insert/delete) ===");
        MyHashSet<String> hs = new MyHashSet<>();
        System.out.println("insert A: " + hs.insert("A"));
        System.out.println("insert B: " + hs.insert("B"));
        System.out.println("insert A again: " + hs.insert("A"));
        System.out.println("size: " + hs.size());
        System.out.println("delete B: " + hs.delete("B"));
        System.out.println("delete C: " + hs.delete("C"));
        System.out.println("size: " + hs.size());
    }

    private static void checkMyArrayList() {
        System.out.println("\n=== MyArrayList (add/get/remove/addAll) ===");
        MyArrayList<Integer> arr = new MyArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        System.out.println("get(1) = " + arr.get(1));
        System.out.println("remove(0) = " + arr.remove(0));
        System.out.println("size = " + arr.size());
        arr.addAll(List.of(10, 20, 30));
        for (Integer x : arr) System.out.print(x + " ");
        System.out.println();
    }
}
