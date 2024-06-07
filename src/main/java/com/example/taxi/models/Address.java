package com.example.taxi.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Address {
    //TODO переделать класс адрес, добавив туда поле имя.
    String unit;
    String name;
    int distance;
}
