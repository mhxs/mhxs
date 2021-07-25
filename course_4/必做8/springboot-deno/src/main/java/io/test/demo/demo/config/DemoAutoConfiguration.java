package io.test.demo.demo.config;

import io.test.demo.demo.ISchool;
import io.test.demo.demo.Klass;
import io.test.demo.demo.School;
import io.test.demo.demo.Student;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(DemoProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@Import(value = {Student.class, Klass.class})
@ConditionalOnProperty(prefix = DemoProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class DemoAutoConfiguration {

    @Bean
    Student student() {
        Student student = new Student();
        student.setId(1);
        student.setBeanName("zhangsan");
        return student;
    }

    @Bean
    Student student2() {
        Student student = new Student();
        student.setId(2);
        student.setBeanName("lisi");
        return student;
    }

    @Bean
    @ConditionalOnBean(value = {Student.class})
    Klass klass(List<Student> student) {
        Klass klass = new Klass();
        klass.setStudents(student);
        return klass;
    }

    @Bean
    @ConditionalOnBean(value = {Student.class, Klass.class})
    ISchool iSchool(@Qualifier("klass") Klass class1, @Qualifier("student") Student student100) {
        return new School(class1, student100);
    }

}
