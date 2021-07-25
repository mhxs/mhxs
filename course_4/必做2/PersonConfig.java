package io.test.demo.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    /**
     * spring boot 自动装配
     */
    @Bean
    public Person person() {
        return new Person();
    }

}
