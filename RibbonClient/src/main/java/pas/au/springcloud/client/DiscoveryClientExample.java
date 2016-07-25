package pas.au.springcloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class DiscoveryClientExample implements CommandLineRunner
{

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void run(String... strings) throws Exception {
        discoveryClient.getInstances("stock-service").forEach((ServiceInstance s) -> {
            System.out.println("---stock-service------");
            System.out.print("Host:" + s.getHost() + ", port:" + s.getPort() + ", uri:" + s.getUri());
            System.out.println("\n---------");

        });
    }
}