package io.test.demo.demo.single;
/**
 * 懒汉式 -- 线程不安全
 */
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3() {
    }

    public static synchronized SingletonObject3 getInstance() {
        if (instance == null) {
            instance = new SingletonObject3();
        }
        return instance;
    }
}