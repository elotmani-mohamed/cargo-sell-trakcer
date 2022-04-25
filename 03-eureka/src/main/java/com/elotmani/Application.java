package com.elotmani;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		String alt = "sanjgSONYcsd";
		

		int before=alt.indexOf("SONY")-1;
		int after=alt.length()-(alt.indexOf("SONY")+"SONY".length())-1;


		System.err.println(after==before);
		
		
	}

}
