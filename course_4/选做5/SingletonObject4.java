package io.test.demo.demo.single;

public class SingletonObject4 {
    private volatile static SingletonObject4 singleton;

    private SingletonObject4() {
    }

    public static SingletonObject4 getSingleton() {
        if (singleton == null) {
            synchronized (SingletonObject4.class) {
                if (singleton == null) {
                    singleton = new SingletonObject4();
                }
            }
        }
        return singleton;
    }
}