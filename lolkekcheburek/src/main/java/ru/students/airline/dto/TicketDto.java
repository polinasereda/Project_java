package ru.students.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok аннотация для автоматического создания геттеров, сеттеров, а также методов equals, hashCode и toString
@NoArgsConstructor // Lombok аннотация для создания конструктора без аргументов
@AllArgsConstructor // Lombok аннотация для создания конструктора со всеми полями в качестве аргументов
public class TicketDto {
    // Класс DTO, представляющий билет на рейс

    String flightNumber; // Номер рейса
    String date; // Дата рейса
    Integer seats; // Количество забронированных мест
}
