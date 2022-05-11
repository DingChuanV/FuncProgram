package com.uin.lambda;

import java.util.function.IntBinaryOperator;

public class demo02 {
    public static void main(String[] args) {
        //快捷键 option+enter 自动转化成lambda表达式
        int i = calculateNum((left, right) -> left + right);
        System.out.println(i);

        //===============

        int i1 = calculateNum((left, right) -> {
            return left + right;
        });
        System.out.println(i1);
    }

    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }

}
