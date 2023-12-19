package ru.students.airline.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.students.airline.services.FileService;
import ru.students.airline.services.FlightService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller // Обозначает, что класс является контроллером в Spring MVC
@RequiredArgsConstructor // Lombok аннотация, автоматически создающая конструктор для всех final полей
public class AdminController {

    private final FlightService flightService; // Сервис для работы с данными о рейсах
    private final FileService fileService; // Сервис для работы с файлами

    @GetMapping(value = "/admin") // Обработчик GET запроса для страницы администратора
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin"); // Указывает на использование шаблона "admin" для отображения страницы
        modelAndView.getModel().put("flights", flightService.getAllFlights()); // Добавляет данные о всех рейсах в модель
        return modelAndView; // Возвращает модель и представление для отображения
    }

    @PostMapping(value = "/upload") // Обработчик POST запроса для загрузки файла
    public void handleFileUpload(@RequestParam("file") MultipartFile file,
                                 HttpServletResponse response) throws IOException {
        fileService.uploadFile(file); // Вызывает метод для загрузки файла
        response.sendRedirect("/admin"); // После загрузки перенаправляет обратно на страницу администратора
    }
}
