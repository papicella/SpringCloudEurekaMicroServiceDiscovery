package pivotal.com.apples.springcloud.services.stock;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "/services")
public class FinanceService
{
    //private final String serviceUri  = "http://finance.yahoo.com/webservice/v1/symbols/%s/quote?format=json";
    private final String serviceUri  = "http://finance.google.com/finance/info?client=ig&q=NASDAQ:%s";

    private final RestTemplate restTemplate = new RestTemplate();


    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/stock", method = RequestMethod.GET, path = "/stock")
    public String sendSms(@RequestParam(value="symbol", required=true) String symbol)
    {
        String jsonResponse =
                restTemplate.getForObject
                        (String.format(serviceUri, symbol), String.class);

        return jsonResponse;
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName)
    {
        return this.discoveryClient.getInstances(applicationName);

    }

}
