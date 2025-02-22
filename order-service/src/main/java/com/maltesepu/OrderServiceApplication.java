package com.maltesepu;

import com.maltesepu.webclient.CustomerClient;
import com.maltesepu.webclient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }


    @Autowired
    private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    @Autowired
    private WebClient.Builder webClientBuildCustomer;

    @Autowired
    private WebClient.Builder webClientBuildProduct;


    @Bean
    WebClient webClientCustomer() {
        return webClientBuildCustomer
                .baseUrl("http://customer-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @Bean
    WebClient webClientProduct() {
        return webClientBuildProduct
                .baseUrl("http://product-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @Bean
    CustomerClient customerClient() {
        HttpServiceProxyFactory factor = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClientCustomer()))
                .build();
        return factor.createClient(CustomerClient.class);
    }

    @Bean
    ProductClient productClientClient() {
        HttpServiceProxyFactory factor = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClientProduct()))
                .build();
        return factor.createClient(ProductClient.class);
    }

}
