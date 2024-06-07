package com.example.taxi.car;

import com.example.taxi.models.Address;
import com.example.taxi.client.Client;
import com.example.taxi.exceptions.TaxiProfitException;
import com.example.taxi.properties.CarProperties;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public abstract class Car {
    CarProperties carProperties;

    @Value("${car.dailyRate}")
    BigDecimal dailyRate;

    @Value("${car.nightRate}")
    BigDecimal nightRate;

    UUID uuid;
    BigDecimal totalIncome;
    List<String> adresses = List.of(
            "berezovayaRosha",
            "kandikulya",
            "stroitel"
    );
    BigDecimal taxiCompanyProfit;

    @Autowired
    public Car(CarProperties carProperties, @Qualifier("uuid") UUID uuid) {
        this.uuid = uuid;
        this.carProperties = carProperties;
    }

    @PostConstruct
    public void init() {
        log.info("Машина создана с номером: {}", uuid);
    }


    /**
     * @param client
     * @param isDay
     * @return На основании адреса, узнаем сколько ехать, и считаем сумму заработанных денег (ставка * км).
     * *50% оставляем себе, 50% идет в таксопарк.
     */
    //TODO переделать метод с изменненным классом Address
    public BigDecimal acceptTheOrder(Client client, boolean isDay) {
        String name = client.getAddress().getName();
        if ((name.contains("berezovayaRosha") || name.contains("kandikulya")
                || name.contains("stroitel"))) {
            BigDecimal rate = isDay ? dailyRate : nightRate;
            return incomeCalculation(rate, client);
        } else {
            throw new TaxiProfitException("Такого адреса нет. Выберите адрес, доступный из тарифной сетки");
        }
    }

    //TODO переделать метод с изменненным классом Address
    private BigDecimal incomeCalculation(BigDecimal rate, Client client) {
        Address address = client.getAddress();
        int distance = address.getDistance();

        rate = rate.multiply(new BigDecimal(distance));
        if (rate.compareTo(BigDecimal.ZERO) > 0) {
            taxiCompanyProfit = rate.divide(new BigDecimal(2), RoundingMode.HALF_UP);
            return taxiCompanyProfit;
        } else {
            throw new TaxiProfitException("Доход таксиста равен нулю или меньше нуля");
        }
    }
}
