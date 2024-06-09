package com.example.taxi.runner;

import com.example.taxi.client.Client;
import com.example.taxi.models.Bluz;
import com.example.taxi.models.Luna;
import com.example.taxi.models.TaxiCompany;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;

@Slf4j
@ComponentScan(basePackages = {
        "com.example.taxi.models",
        "com.example.taxi.properties",
        "com.example.taxi.client",
        "com.example.taxi.config"
})
@ConfigurationPropertiesScan("com.example.taxi.properties")
public class Runner {

    public static void main(String[] args) {
        log.info("Starting application");
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
        log.info("Application started");
        TaxiCompany taxiCompany = context.getBean(TaxiCompany.class);
        Map<String, Client> clientMap = context.getBeansOfType(Client.class);
        Client client1 = clientMap.remove("client1");
        Client client2 = clientMap.remove("client2");
        Client client3 = clientMap.remove("client3");
        //Bluz bluz = context.getBean(Bluz.class);
        Luna luna = context.getBean(Luna.class);
        taxiCompany.acceptOrder(client2, true, luna);
        taxiCompany.acceptOrder(client3, false, luna);
        taxiCompany.acceptOrder(client1, false, luna);
        taxiCompany.createReport(luna);
        log.info("Application finished");
        System.out.println(taxiCompany);


    }
}
