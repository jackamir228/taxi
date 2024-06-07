package com.example.taxi.config;

import com.example.taxi.client.Client;
import com.example.taxi.models.Address;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.UUID;

@ComponentScan(basePackages = {
        "models",
        "properties",
        "client"
})
@Configuration
public class TaxiConfig {
    @Bean
    UUID uuid() {
        return UUID.randomUUID();
    }

    @Bean("uuidForLuna")
    UUID uuidForLuna() {
        return UUID.randomUUID();
    }

    @Bean("uuidForBluz")
    UUID uuidForBluz() {
        return UUID.randomUUID();
    }


    @Bean("bereza")
    Address address1() {
        return new Address("km", "berezovayaRosha", 10);
    }

    @Bean("kandikulya")
    Address address3() {
        return new Address("km", "kandikulya", 4);
    }

    @Bean("stroitel")
    Address address2() {
        return new Address("km", "stroitel", 12);
    }

    @Bean("client1")
    Client client1(@Qualifier("bereza") Address address1) {
        return new Client(address1());
    }

    @Bean("client2")
    Client client2(@Qualifier("kandikulya") Address address2) {
        return new Client(address2());
    }

    @Bean("client3")
    Client client3(@Qualifier("stroitel") Address address3) {
        return new Client(address3());
    }

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
