package com.singh.backend.mdtohtml;

import com.singh.backend.mdtohtml.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class MdToHtmlApplication {

	/**
	 *  Main entry point to web application.
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MdToHtmlApplication.class, args);
	}
}
