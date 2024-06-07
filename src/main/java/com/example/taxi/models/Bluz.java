package com.example.taxi.models;

import com.example.taxi.car.Car;
import com.example.taxi.properties.CarProperties;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@Profile("bluz")
@Component
public class Bluz extends Car {
    public Bluz(CarProperties carProperties, @Qualifier("uuidForBluz") UUID uuid) {
        super(carProperties, uuid);
    }

    @PostConstruct
    @Override
    public void init() {
        log.info("Машина {} создана, с номером: {}", this, getUuid());
    }
}
