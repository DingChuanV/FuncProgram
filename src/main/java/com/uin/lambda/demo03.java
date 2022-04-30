package com.uin.lambda;

import java.util.function.IntPredicate;

public class demo03 {
    public static void main(String[] args) {
        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                //拿偶数
                return value % 2 == 0;
            }
        });

        // ======

        printNum((item) -> {
            return item % 2 == 0;
        });

    }

    public static void printNum(IntPredicate predicate) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            //根据给定参数评估此谓词。
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }
}
