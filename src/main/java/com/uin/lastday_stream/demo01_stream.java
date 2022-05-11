package com.uin.lastday_stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class demo01_stream {
    public static Map<String, String> map1 = null;
    public static Map<String, String> map2 = null;

    public static void main(String[] args) {
        map1 = new HashMap<>();
        map1.put("name", "dog");
        map1.put("age", "3");

        map2 = new HashMap<>();
        map2.put("name", "cat");
        map2.put("age", "2");

        //测试块
        List<Integer> ages_from_stream = getAges_from_stream();
        System.out.println("提取到age的list集合" + ages_from_stream);
    }

    /**
     * 需求：提取对象中的某个值生成一个新的List
     *
     * @return java.util.List<java.lang.Integer>
     * @author wanglufei
     * @date 2022/5/11 10:38 AM
     */
    public static List<Integer> getAges_from_stream() {
        List<Integer> collect = Stream.of(map1, map2)
                .map(map1 -> Integer.valueOf(map1.get("age")))
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * 需求：统计一个对象某个值的总和
     *
     * @return java.lang.Integer
     * @author wanglufei
     * @date 2022/5/11 10:41 AM
     */
    public static Optional<Integer> reduce_Object_total() {
        Optional<Integer> reduce = Stream.of(map1, map2)
                .distinct()
                .map(age -> Integer.valueOf(age.get("age")))
                .reduce((integer, integer2) -> integer + integer2);
        return reduce;
    }

}
