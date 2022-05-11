package com.uin.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.uin.stream.Author;

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
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        //test7();
        //test8();
        //test9();
        //test10();
        test11();
        //test12();
    }

    private static void test12() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //使用串行流
        Optional<Integer> reduce = stream
                .filter(item -> item > 4)
                .reduce((result, element) -> result + element);
        System.out.println(reduce.orElseGet(() -> Integer.valueOf(String.valueOf(reduce))));

        //使用并行流
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> reduce1 = stream1
                .parallel()
                //调试作用
                .peek(integer -> System.out.println(integer + "-----" + Thread.currentThread().getName()))
                .filter(item -> item > 4)
                .reduce((result, element) -> result + element);
        System.out.println(reduce1.get());
    }

    private static void test11() {
        List<Author> authors = getAuthors();
        authors.stream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .filter(age -> age > 18)
                .map(age -> age + 2)
                .forEach(System.out::println);

        //减少装箱和拆箱的时间 优化后的代码
        List<Author> authors1 = getAuthors();
        authors1.stream()
                .mapToInt(item -> item.getAge())
                .map(age -> age + 10)
                .filter(age -> age > 18)
                .map(age -> age + 2)
                .forEach(System.out::println);
    }

    private static void test10() {
        //使用reduce的一个参数值 求年龄的最小值
        List<Author> authors = getAuthors();
        Optional<Integer> reduce = authors.stream()
                .map(item -> item.getAge())
                .distinct()
                .reduce((result, element) -> Math.min(result, element));
        //ifPresent 不为空  如果存在值，则使用该值调用指定的使用者，否则不执行任何操作
        reduce.ifPresent(age -> System.out.println(age));
    }

    private static void test9() {
        //使用reduce 求所有作者的年龄中最小的值
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .map(item -> item.getAge())
                .distinct()
                .reduce(Integer.MAX_VALUE, (result, element) -> result < element ? result : element);
        System.out.println(reduce);
    }

    private static void test8() {
        //使用reduce求所有作者中年龄最大的
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .map(item -> item.getAge())
                .distinct()
                .reduce(Integer.MIN_VALUE, (result, element) -> Math.max(result, element));
        //或者使用三元运算符
        // return result>element? result:element;
        // result < element? element:result
        System.out.println(reduce);
    }

    private static void test7() {
        //reduce 归并
        //使用reduce求所有作者的年龄之和
        List<Author> authors = getAuthors();
        //mapreduce 模式
        Integer reduce = authors.stream()
                .map(item -> {
                    return item.getAge();
                })
                .distinct()
                .reduce(0, (result, element) -> result + element);
        System.out.println(reduce);
    }

    private static void test6() {
        List<Author> authors = getAuthors();
        Map<String, List<Book>> collect = authors.stream()
                .distinct()
                .collect(Collectors.toMap(item -> item.getName(), item -> item.getBooks()));
        collect.forEach((s, books) -> {
            System.out.println(s);
            System.out.println(books);
        });
    }

    private static void test5() {
        List<Author> authors = getAuthors();
        //获取一个map集合，map的key为作者名，value为List<Book>
        Map<String, List<Book>> collect2 = authors.stream().collect(Collectors.toMap(new Function<Author, String>() {
            @Override
            public String apply(Author author) {
                return author.getName();
            }
        }, new Function<Author, List<Book>>() {
            @Override
            public List<Book> apply(Author author) {
                return author.getBooks();
            }
        }));
        System.out.println(collect2);
    }

    private static void test4() {
        //获取一个所有作者名字的集合
        List<Author> authors = getAuthors();
        List<String> collect = authors.stream().map(item -> item.getName())
                .collect(Collectors.toList());
        System.out.println(collect);

        //获取一个书的set集合
        Set<Book> collect1 = authors.stream().flatMap(item -> item.getBooks().stream())
                .collect(Collectors.toSet());
        System.out.println(collect1);

    }

    private static void test3() {
        //分别获取这些作家的所处书籍的做高分和最低分
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream().flatMap(item -> item.getBooks().stream())
                .map(item -> item.getScore())
                .max((a1, a2) -> {
                    return a1 - a2;
                });
        System.out.println(max.get());

        Optional<Integer> min = authors.stream().flatMap(item -> item.getBooks().stream())
                .map(item -> item.getScore())
                .min((a1, a2) -> {
                    return a1 - a2;
                });
        System.out.println(min.get());
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
        Author author3 = new Author(3L, "蒙1多", 14, "一个从菜刀中明悟1哲理的祖安人", null);

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
