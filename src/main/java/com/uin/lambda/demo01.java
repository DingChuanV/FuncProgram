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

        /**
         * 简化的规则
         *  1.如果我们的匿名内部类是一个接口
         *  2.并且它当中只有一个抽象方法需要被重写
         */

        //我们使用Lambda的格式对其进行修改
        new Thread(() -> {System.out.println("你知道吗 我很爱五一假期。。。。");}).start();
    }
}
