package com.uin.optional;

import com.uin.stream.Author;
import com.uin.stream.Book;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class demo1 {
    public static void main(String[] args) {
        Author author = getAuthor();
        //Optional<Author> o = Optional.of(null);
        //o.ifPresent(item-> System.out.println(item.getName()));


        //对数据进行封装
        Optional<Author> optional = Optional.ofNullable(author);

        //进行消费 orElseGet
        Author author1 = optional.orElseGet(() -> new Author(2L, "xxx", 12, "xx23", null));
        System.out.println(author1.getName());

        //进行消费 ifPresent
        optional.ifPresent(item -> {
            System.out.println(item.getName());
        });

        //进行消费 orElseThrow
        try {
            optional.orElseThrow((Supplier<Throwable>) () -> new ClassNotFoundException());
            System.out.println(author);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }


        Optional<Author> authorOptional = getAuthorOptional();
        authorOptional.ifPresent(item -> System.out.println(item.getName()));

        Optional<Author> authorEmpty = getAuthorEmpty();
//        authorEmpty
    }

    //数据转换
    private static Optional<Author> map() {
        Author author = getAuthor();
        //对数据进行封装
        Optional<Author> optional = Optional.ofNullable(author);
        Optional<List<Book>> books = optional.map((item) -> item.getBooks());
        books.ifPresent(new Consumer<List<Book>>() {
            @Override
            public void accept(List<Book> books) {
                books.forEach(book -> System.out.println(book.getName()));
            }
        });
        return Optional.ofNullable(author);
    }

    //过滤数据
    private static Optional<Author> filter() {
        Author author = getAuthor();
        //对数据进行封装
        Optional<Author> optional = Optional.ofNullable(author);
        optional.filter(author1 -> author1.getAge() > 30);
        return Optional.ofNullable(author);
    }

    private static Optional<Author> getAuthorEmpty() {
        Author author = new Author(1L, "xxx", 13, "xxx", null);
        //就好比我们在使用mybatis去数据库查询，按照我的条件，查到的就是null，此时我们的代码就会报控制针异常
        return author == null ? Optional.empty() : Optional.ofNullable(author);
    }

    private static Optional<Author> getAuthorOptional() {
        Author author = new Author(1L, "xxx", 13, "xxx", null);
        //就好比我们在使用mybatis去数据库查询，按照我的条件，查到的就是null，此时我们的代码就会报控制针异常
        return Optional.of(author);
    }

    private static Author getAuthor() {
        Author author = new Author(1L, "xxx", 13, "xxx", null);
        //就好比我们在使用mybatis去数据库查询，按照我的条件，查到的就是null，此时我们的代码就会报控制针异常
        return null;
    }
}
