package ru.students.airline.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.students.airline.security.CustomAuthProvider;

@Controller // Аннотация, указывающая, что данный класс является контроллером в Spring MVC.
@RequestMapping("/auth") // Аннотация, указывающая, что все методы в этом контроллере будут обрабатывать запросы, начинающиеся с "/auth".
@RequiredArgsConstructor // Аннотация Lombok, создающая конструктор для всех final полей. В данном случае не имеет прямого эффекта, так как нет final полей.
public class AuthController {

    @GetMapping("/login") // Аннотация для обработки HTTP GET запросов на пути "/auth/login".
    public String loginPage() {
        return "auth/login"; // Возвращает имя представления, которое будет отображать страницу входа. Здесь оно указывает на шаблон 'auth/login'.
    }
}

