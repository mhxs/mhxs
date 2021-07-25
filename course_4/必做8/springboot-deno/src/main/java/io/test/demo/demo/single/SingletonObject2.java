package io.test.demo.demo.single;
/**
 * 懒汉式 -- 线程不安全
 */
public class SingletonObject2 {
    private static SingletonObject2 instance;

    private SingletonObject2() {
    }

    public static SingletonObject2 getInstance() {
        if (instance == null) {
            instance = new SingletonObject2();
        }
        return instance;
    }
}