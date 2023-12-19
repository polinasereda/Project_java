package ru.students.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data // Lombok аннотация для автоматического создания геттеров, сеттеров, equals, hashCode и toString
@NoArgsConstructor // Lombok аннотация для создания конструктора без аргументов
@AllArgsConstructor // Lombok аннотация для создания конструктора со всеми аргументами
public class FlightDto {

    String number; // Уникальный номер рейса

    private String departureCity; // Город отправления

    private String arrivalCity; // Город прибытия

    private LocalTime departureTime; // Время отправления

    private LocalTime arrivalTime; // Время прибытия

    private Integer cost; // Стоимость билета

    private Integer seats; // Количество мест в самолете

    private String weekdays; // Дни недели, в которые осуществляется рейс
}
