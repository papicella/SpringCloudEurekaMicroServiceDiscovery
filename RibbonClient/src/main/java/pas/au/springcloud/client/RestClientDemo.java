package pas.au.springcloud.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestClientDemo
{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/stock-test", method = RequestMethod.GET, path = "/stock-test")
    public String getStockData(@RequestParam(value="symbol", required=true) String symbol)
    {
        return makeRestCall(symbol);
    }

    @HystrixCommand(fallbackMethod = "displayIssueString")
    private String makeRestCall (String symbol)
    {
        String serviceUri = "http://stock-service/services/stock?symbol=%s";

        String jsonResponse =
                restTemplate.getForObject
                        (String.format(serviceUri, symbol), String.class);

        return jsonResponse;
    }

    private String displayIssueString()
    {
        return "Unable to retrieve STOCK data";
    }

    @RequestMapping(value = "/stock-service-info", method = RequestMethod.GET, path = "/stock-service-info")
    public String getStockDataService()
    {
        StringBuffer sb = new StringBuffer();

        discoveryClient.getInstances("stock-service").forEach((ServiceInstance s) -> {
            sb.append("---stock-service---\n");
            sb.append("Host:" + s.getHost() + ", port:" + s.getPort() + ", uri:" + s.getUri() + "\n");
        });

        return sb.toString();
    }

}
