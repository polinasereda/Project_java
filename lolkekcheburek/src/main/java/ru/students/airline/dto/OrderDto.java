package ru.students.airline.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data // Lombok аннотация для автоматического создания геттеров, сеттеров, equals, hashCode и toString
@RequiredArgsConstructor // Lombok аннотация, создающая конструктор для всех final и non-null полей
public class OrderDto {
    Long id; // Уникальный идентификатор заказа
    FlightDto flight; // Данные о рейсе, связанные с этим заказом
    String flightDate; // Дата полета
    String username; // Имя пользователя, сделавшего заказ
    List<PassengerDto> passenger; // Список пассажиров в заказе
    Boolean needRegistration; // Флаг, указывающий, нужна ли авторегистрация
    Boolean needInsurance; // Флаг, указывающий, нужна ли страховка

    // Методы ниже используются для расчета общей стоимости заказа.

    public long luggageCount() {
        // Считает количество пассажиров, которым нужен багаж
        return passenger.stream().filter((x) -> x.needLuggage).count();
    }

    public int insuranceCount() {
        // Возвращает количество пассажиров, которым нужна страховка (если она включена в заказ)
        if (needInsurance) {
            return passenger.size();
        } else {
            return 0;
        }
    }

    public int registrationCount() {
        // Возвращает количество пассажиров, которым нужна авторегистрация (если она включена в заказ)
        if (needRegistration) {
            return passenger.size();
        } else {
            return 0;
        }
    }
}
