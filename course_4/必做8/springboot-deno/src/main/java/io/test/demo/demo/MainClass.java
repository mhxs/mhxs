package io.test.demo.demo;

import io.test.demo.demo.config.DemoAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoAutoConfiguration.class);
        School iSchool = (School)context.getBean("iSchool");
        iSchool.ding();
        System.out.println();
    }

}
