package cl.duoc.chofer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ChoferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoferServiceApplication.class, args);
	}

}
