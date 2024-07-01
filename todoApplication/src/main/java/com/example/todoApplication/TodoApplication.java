package com.example.todoApplication;

import com.example.todoApplication.service.TodoServices;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;



@SpringBootApplication
public class TodoApplication {

	@Autowired
	private TodoServices todoServices;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);

	}

//	@PostConstruct
//	public void run() {
//		System.out.printf("TEST TEST TEST");
//		todoServices.init();
//	}

}
