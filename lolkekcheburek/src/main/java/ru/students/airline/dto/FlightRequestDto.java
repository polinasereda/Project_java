package ru.students.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok аннотация для автоматического создания геттеров, сеттеров, equals, hashCode и toString методов
@NoArgsConstructor // Lombok аннотация для создания конструктора без аргументов
@AllArgsConstructor // Lombok аннотация для создания конструктора со всеми полями класса в качестве аргументов
public class FlightRequestDto {
    // Этот класс используется для передачи данных запроса на поиск рейсов.
    // Пользователь заполняет эти данные на веб-странице, и они переносятся в этот объект.

    private String date; // Дата, на которую пользователь ищет рейс

    private String arrivalCity; // Город прибытия, который пользователь хочет найти

    private String departureCity; // Город отправления, который пользователь хочет найти

    private int neededSeats; // Количество необходимых мест, которое пользователь хочет забронировать
}
