package com.uin.lambda;

import java.util.function.IntConsumer;

public class demo05 {
    public static void main(String[] args) {
        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
        //==============
        foreachArr((item) -> {
            System.out.println(item);
        });
    }

    public static void foreachArr(IntConsumer consumer) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }
}
