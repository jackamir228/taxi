package com.example.taxi.runner;

import com.example.taxi.client.Client;
import com.example.taxi.models.Bluz;
import com.example.taxi.models.Luna;
import com.example.taxi.models.TaxiCompany;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;


@ComponentScan(basePackages = {
        "com.example.taxi.models",
        "com.example.taxi.properties",
        "com.example.taxi.client",
        "com.example.taxi.config"
})
@ConfigurationPropertiesScan("com.example.taxi.properties")
public class Runner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
        TaxiCompany taxiCompany = context.getBean(TaxiCompany.class);
        System.out.println(taxiCompany.getName());
        Map<String, Client> clientMap = context.getBeansOfType(Client.class);
        Client client1 = clientMap.remove("client1");
        Client client2 = clientMap.remove("client2");
        Client client3 = clientMap.remove("client3");
        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);
        Luna luna = context.getBean(Luna.class);
        Bluz bluz = context.getBean(Bluz.class);
        System.out.println(taxiCompany.acceptOrder(client1, true, bluz));
        taxiCompany.createReport(bluz);


    }
}
