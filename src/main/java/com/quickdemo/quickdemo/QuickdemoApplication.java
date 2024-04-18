package com.quickdemo.quickdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class QuickdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickdemoApplication.class, args);
	}

}
