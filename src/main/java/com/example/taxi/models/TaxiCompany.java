package com.example.taxi.models;

import com.example.taxi.car.Car;
import com.example.taxi.client.Client;
import com.example.taxi.properties.TaxiCompanyProperties;
import com.example.taxi.exceptions.TaxiProfitException;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Есть таксопарк, свойства: имя (из ресурсов), сумма заработанных денег (со всех такси).
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@Component
@Configuration
@ConfigurationProperties(prefix = "taxi-company")
public class TaxiCompany {
    @Value("${taxi-company.name}")
    String name;

    BigDecimal taxiProfit;

    TaxiCompanyProperties taxiCompanyProperties;

    public TaxiCompany(TaxiCompanyProperties taxiCompanyProperties) {
        this.taxiCompanyProperties = taxiCompanyProperties;
    }

    @PostConstruct
    public void init() {
        log.info("Таксопарк создан {}", this);
    }

    public BigDecimal acceptOrder(Client client, boolean isDay, Car car) {
        try {
            car.acceptTheOrder(client, isDay);
            taxiProfit = car.getTaxiCompanyProfit();
        } catch (TaxiProfitException ignored) {
                return taxiProfit;
        }
        return car.getTotalIncome();
    }

    public void createReport(Car... cars) {
        try (FileWriter writer = new FileWriter("C:\\Users\\gilma\\IdeaProjects\\taxi\\src\\main\\resources\\report.txt", true)) {
            for (Car car : cars) {
                writer.write( "\n\n" + taxiCompanyProperties.getName());
                writer.write("\nЗаработано: " + taxiProfit);
                writer.write("\nВодитель машины " + car.getUuid() + " заработал: " + car.getTotalIncome());
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}