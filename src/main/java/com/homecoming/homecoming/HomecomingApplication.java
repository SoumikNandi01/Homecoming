package com.homecoming.homecoming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class HomecomingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomecomingApplication.class, args);
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
