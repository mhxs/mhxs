package com.mhxs.blog.test3;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    private byte[] loadByte(String name) throws Exception {
        FileInputStream fis = new FileInputStream(name);
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        for (int i = 0; i < len; i++) {
            data[i] = (byte) (255 - data[i]);
        }
        fis.close();
        return data;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = loadByte(name);
            String[] split = name.split("\\\\");
            return super.defineClass(split[split.length - 1].split("\\.")[0], bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader mylassLoader = new MyClassLoader();
        Class<?> xHelloClass = mylassLoader.findClass("E:\\test\\Hello.xlass");
        Object instance = xHelloClass.newInstance();
        Method hello = xHelloClass.getDeclaredMethod("hello");
        hello.invoke(instance);
    }
}
