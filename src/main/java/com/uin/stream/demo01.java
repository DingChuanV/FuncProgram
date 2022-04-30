package com.uin.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class demo01 {
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        System.out.println(authors);
    }

    private static List<Author> getAuthors() {
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author1 = new Author(2L, "呀啦嗦", 15, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(3L, "一", 14, "一个从菜刀中明悟哲理的祖安人", null);
        Author author3 = new Author(4L, "蒙1多", 14, "一个从菜刀中明悟哲理的祖安人", null);

        //书籍列表

        List<Book> list1 = new ArrayList<>();
        List<Book> list2 = new ArrayList<>();
        List<Book> list3 = new ArrayList<>();
        List<Book> list4 = new ArrayList<>();

        list1.add(new Book(1L, "xxxxx", "xx1x", 88, "xxx"));
        list1.add(new Book(2L, "xxxxx", "xx2x", 88, "xxx"));

        list2.add(new Book(3L, "xxx", "x2xx", 85, "xxx"));
        list2.add(new Book(4L, "xxx", "xx2x", 85, "xxx"));
        list2.add(new Book(5L, "xxx", "x2xx", 56, "xxx"));

        list3.add(new Book(6L, "xxx", "xx3x", 56, "xxx"));
        list3.add(new Book(7L, "xxx", "xx3x", 100, "xxx"));
        list3.add(new Book(8L, "xxx", "x3xx", 100, "xxx"));
        list3.add(new Book(9L, "xxx", "xx4x", 56, "xxx"));


        list4.add(new Book(6L, "xxx", "xx5x", 56, "xxx"));
        list4.add(new Book(6L, "xxx", "x6xx", 156, "xxx"));
        list4.add(new Book(6L, "xxx", "xx7x", 516, "xxx"));
        list4.add(new Book(6L, "xxx", "x8xx", 256, "xxx"));

        author.setBooks(list1);
        author1.setBooks(list2);
        author2.setBooks(list3);
        author3.setBooks(list4);

        ArrayList<Author> authors = new ArrayList<>(Arrays.asList(author, author1, author2, author3));
        return authors;
    }

}
