package com.example.taxi.models;

import com.example.taxi.car.Car;
import com.example.taxi.properties.CarProperties;
import jakarta.annotation.PostConstruct;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j
@ToString
@ConditionalOnMissingBean(value = {Bluz.class, Luna.class})
public class CarDefault extends Car {
    CarProperties carProperties;

    @Autowired
    public CarDefault(CarProperties carProperties,  @Qualifier("uuid")UUID uuid) {
        super(carProperties, uuid);
        this.carProperties = carProperties;
    }

    @PostConstruct
    @Override
    public void init() {
        log.info("Была создана стандартная машина с номером {}." +
                " Вероятнее всего ошибка в файле конфигурации профилей", getUuid());
    }
}
