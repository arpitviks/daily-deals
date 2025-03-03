package com.dailyDeals.dailyDeals_v6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DailyDealsV6Application {

	public static void main(String[] args) {
		SpringApplication.run(DailyDealsV6Application.class, args);
	}

}
