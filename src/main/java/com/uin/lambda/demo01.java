package com.uin.lambda;

public class demo01 {
    public static void main(String[] args) {
        //我们在创建线程启动时可以使用匿名内部类的写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("你知道吗 我很爱五一假期");
            }
        }).start();
    }
}
