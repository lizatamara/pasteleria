package cl.duoc.administrador_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AdministradorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministradorServiceApplication.class, args);
	}

}
