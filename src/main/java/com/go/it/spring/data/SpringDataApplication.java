package com.go.it.spring.data;

import com.go.it.spring.data.configuration.ContextFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = FlywayAutoConfiguration.class)
public class SpringDataApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(SpringDataApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.contextFactory(new ContextFactory())
				.build()
				.run(args);
	}

}
