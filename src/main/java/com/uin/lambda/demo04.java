package com.uin.lambda;

import java.util.function.Function;

public class demo04 {
    public static void main(String[] args) {
        Integer integer = typeConver(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        });
        System.out.println(integer);
        // =======

        Integer conver = typeConver((String s) -> {
            return Integer.valueOf(s);
        });
        System.out.println(conver);
    }

    public static <R> R typeConver(Function<String, R> function) {
        String str = "1234";
        R result = function.apply(str);
        return result;
    }
}
