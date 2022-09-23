package com.example.producer;

import com.example.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@SpringBootApplication
@RestController
public class ConsumerApplication {

	private final Logger log = LoggerFactory.getLogger(ConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public Consumer<Sensor> process()  {
		return sensor -> log.info("Sensor Received: {}}", sensor);
	}

}
