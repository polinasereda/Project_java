package ru.students.airline.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Lombok аннотация для автоматического создания геттеров, сеттеров, equals, hashCode и toString методов
@RequiredArgsConstructor // Lombok аннотация, создающая конструктор для всех final и non-null полей (в данном классе нет таких полей, но аннотация оставлена для будущего использования)
public class PassengerDto {
    // Этот класс представляет собой Data Transfer Object (DTO) для пассажиров

    String name; // Имя пассажира
    String surname; // Фамилия пассажира
    String passport; // Номер паспорта пассажира
    String birthDate; // Дата рождения пассажира в виде строки
    Boolean needLuggage; // Флаг, указывающий, нужен ли пассажиру багаж
}
