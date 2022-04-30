package com.uin.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //用于去重
public class Author {
    private Long id;
    private String name;
    private Integer age;
    /**
     * 简介
     */
    private String intro;
    private List<Book> books;
}
