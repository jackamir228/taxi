package com.example.taxi.client;

import com.example.taxi.models.Address;
import jakarta.annotation.PostConstruct;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@ConfigurationProperties(prefix = "client")
public class Client {
   Address address;

    @Autowired
    public Client(Address address) {
        this.address = address;
    }

    @PostConstruct
    public void init() {
        log.info("Клиент создан{}", this);
    }
}
