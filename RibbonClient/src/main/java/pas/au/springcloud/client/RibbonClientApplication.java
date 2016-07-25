package pas.au.springcloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableCircuitBreaker
//@EnableDiscoveryClient (or @EnableEurekaClient) as identicle
@EnableDiscoveryClient
public class RibbonClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonClientApplication.class, args);
	}

}
