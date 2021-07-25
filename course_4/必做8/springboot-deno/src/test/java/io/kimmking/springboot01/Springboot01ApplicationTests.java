package io.kimmking.springboot01;

import io.test.demo.demo.config.DemoAutoConfiguration;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class Springboot01ApplicationTests {


	@Test
	public void contextLoads() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoAutoConfiguration.class);
		Object iSchool = context.getBean("iSchool");
		System.out.println(iSchool);
	}

}
