package com.example.producer;

import com.example.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

@SpringBootApplication
@RestController
public class ProducerApplication {

	private Random random = new Random();

	private BlockingQueue<Sensor> unbounded = new LinkedBlockingQueue<>();

	private Logger logger = LoggerFactory.getLogger(ProducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	private Sensor randomSensor() {
		Sensor sensor = new Sensor();
		sensor.setId(UUID.randomUUID().toString() + "-v1");
		sensor.setAcceleration(random.nextFloat() * 10);
		sensor.setVelocity(random.nextFloat() * 100);
		sensor.setTemperature(random.nextFloat() * 50);
		return sensor;
	}

	@PostMapping("/messages")
	public String sendMessage() {
		Sensor sensor = randomSensor();
		logger.info("Sensor Sent: {}", sensor);
		unbounded.offer(sensor);
		return "ok, have fun with v1 payload!";
	}

	@Bean
	public Supplier<Sensor> supplier() {
		return () -> unbounded.poll();
	}

}
