package io.test.demo.demo;

import org.springframework.stereotype.Component;

/**
 * 通过注解进行装配
 * @Component @Service @Repository
 */
@Component
public class Person {

    private String name;

    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
