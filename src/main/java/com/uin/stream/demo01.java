package com.uin.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class demo01 {
    public static void main(String[] args) {
        //List<Author> authors = getAuthors();
        //System.out.println(authors);
//        List<Author> authors = getAuthors();
//        authors.stream()
//                .distinct()
//                .filter((item) -> {
//                    return item.getAge() < 18;
//                })
//                .forEach((item) -> {
//                    System.out.printf(item.getName() + ",");
//                });
        //test1();
        test2();
    }

    private static void test2() {
        //打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学，爱情
        List<Author> authors = getAuthors();

        authors.stream()
                .flatMap(item -> item.getBooks().stream())
                .distinct()
                .flatMap(items -> Arrays.stream(items.getCategory().split(",")))
                .distinct()
                .forEach(item -> System.out.println(item));

    }

    /**
     * map只能把一个对象转换成另一个对象来作为流中的元素。
     * 而flatmap可以把一个对象转换成多个对象作为流中的元素。
     *
     * @author wanglufei
     * @date 2022/5/1 8:51 AM
     */
    private static void test1() {
        List<Author> authors = getAuthors();
        //打印所有的书籍，要求对重复的元素进行去重
//        List<List<Book>> collect = authors.stream().map((item) -> {
//            return item.getBooks();
//        }).collect(Collectors.toList());
//        System.out.println(collect);

        authors.stream().distinct().flatMap((item) -> {
            //Stream<Book> stream = item.getBooks().stream();
            return item.getBooks().stream();
        }).forEach(item -> System.out.println(item));
    }

    public static List<Author> getAuthors() {
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author1 = new Author(2L, "呀啦嗦", 15, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(3L, "一", 14, "一个从菜刀中明悟哲理的祖安人", null);
        Author author3 = new Author(4L, "蒙1多", 14, "一个从菜刀中明悟1哲理的祖安人", null);

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

        list3.add(new Book(6L, "xxx", "xx3x,xxx23x", 56, "xxx"));
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
