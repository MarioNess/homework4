package sk.ness.mario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        AtomicInteger sum1 = new AtomicInteger();
        AtomicInteger sum2 = new AtomicInteger();
        {
            Stream.iterate(1, A -> A + 1)
                    .limit(1000)
                    .filter(A -> A % 3 == 0)
                    .filter(A -> A % 5 == 0)
                    .filter(A -> A % 7 != 0)
                    .forEach(A -> sum1.addAndGet(A));
            System.out.println("Sum of all numbers between 1 and 10000 that are dividable by both 3 and 5 but not dividable by 7: ");
            System.out.println(sum1 +"\n");

            System.out.println("Second solution: ");
            System.out.println(Stream.iterate(1, A -> A + 1)
                                    .limit(1000)
                                    .filter(A -> A % 3 == 0)
                                    .filter(A -> A % 5 == 0)
                                    .filter(A -> A % 7 != 0)
                                            .collect(Collectors.summingInt(A -> A))
                                );

        }
        {
            System.out.println("\nFirst 100 even numbers that are not dividable by 8:");
            Stream.iterate(1, A -> A + 1)
                    .limit(100)
                    .filter(A -> A % 2 == 0)
                    .filter(A -> A % 8 != 0)
                    .forEach(A -> {
                        System.out.print(A + " ");
                        sum2.addAndGet(A);});
        }
        System.out.println();System.out.println();
        List<Book> list = new ArrayList<>();
        list.add(new Book("Pan prstenov 1",12));
        list.add(new Book("Pan prstenov 2",15));
        list.add(new Book("Pan prstenov 3",200));
        list.add(new Book("Hobbit",120));
        list.add(new Book("Zaklinac 1",12));
        list.add(new Book("Zaklinac 2",123));
        list.add(new Book("Zaklinac 3",125));
        list.add(new Book("Zaklinac 4",128));
        list.add(new Book("Zaklinac 5",11));
        list.add(new Book("Zaklinac 6",10));
        list.add(new Book("Zaklinac 8",8));
        list.add(new Book("Ako vycvicit draka",8));
        list.add(new Book("Zak",8));

        System.out.println("Names (in alphabetic order) of all books that have price smaller than 100.");
        list.stream().sorted(Comparator.comparing(Book::getName)).
                filter(A -> A.getPrice() < 100)
                .forEach(A -> System.out.println(A.getName()));

        System.out.println("Books have name shorter than 5 characters.");
        System.out.println(list.stream().filter(A -> A.getName().length() < 5).count());

        System.out.println("Average price of the book in the list.");
        System.out.println(list.stream().collect(Collectors.averagingDouble(A -> A.getPrice())));

        System.out.println("all books in list are cheaper than 500.");
        System.out.println(list.stream().allMatch(A -> A.getPrice() <500));
    }
    }
