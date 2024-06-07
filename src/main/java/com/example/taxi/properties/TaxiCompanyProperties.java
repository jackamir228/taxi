package com.example.taxi.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
@ConfigurationProperties(prefix = "taxi-company")
@Configuration
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TaxiCompanyProperties {
    String name;
    BigDecimal taxiProfit;
}
