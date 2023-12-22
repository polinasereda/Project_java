package ru.students.airline.security; // Указывает на принадлежность класса к указанному пакету.

import org.springframework.security.core.Authentication; // Импорт интерфейса Authentication из Spring Security, представляющего информацию об аутентификации текущего пользователя.
import org.springframework.security.core.GrantedAuthority; // Импорт интерфейса GrantedAuthority из Spring Security, представляющего права доступа пользователя.
import org.springframework.security.web.authentication.AuthenticationSuccessHandler; // Импорт интерфейса AuthenticationSuccessHandler из Spring Security, предоставляющего обработчик успешной аутентификации.
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler; // Импорт класса SimpleUrlAuthenticationSuccessHandler из Spring Security, предоставляющего простой обработчик успешной аутентификации с возможностью настройки URL для перенаправления.
import org.springframework.stereotype.Component;// Импорт аннотации Component из Spring Framework, указывающей, что этот класс является компонентом Spring и должен быть автоматически обнаружен и зарегистрирован в контексте Spring.

import javax.servlet.ServletException; // Импорт исключения ServletException, которое может быть выброшено в случае ошибок при обработке запросов.
import javax.servlet.http.HttpServletRequest; // Импорт класса HttpServletRequest, представляющего HTTP-запрос.
import javax.servlet.http.HttpServletResponse; // Импорт класса HttpServletResponse, представляющего HTTP-ответ.
import java.io.IOException;// Импорт исключения IOException, которое может быть выброшено в случае ошибок ввода-вывода.
import java.util.Collection;// Импорт интерфейса Collection из пакета java.util, представляющего коллекцию объектов.

@Component // Аннотация, указывающая, что этот класс является компонентом Spring и должен быть управляемым контейнером Spring.
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler { // Объявление класса CustomAuthenticationSuccessHandler, который реализует интерфейс AuthenticationSuccessHandler.

    private final String USER_PAGE = "/"; //  Объявление константы USER_PAGE с URL для перенаправления пользователя.
    private final String ADMIN_PAGE = "/admin"; // Объявление константы ADMIN_PAGE с URL для перенаправления администратора.

    private final SimpleUrlAuthenticationSuccessHandler userSuccessHandler = // Создание экземпляра SimpleUrlAuthenticationSuccessHandler для обработки успешной аутентификации пользователя с указанием URL для перенаправления.
            new SimpleUrlAuthenticationSuccessHandler(USER_PAGE); // когда зашел user вернет USER_PAGE
    private final SimpleUrlAuthenticationSuccessHandler adminSuccessHandler = // Создание экземпляра SimpleUrlAuthenticationSuccessHandler для обработки успешной аутентификации администратора с указанием URL для перенаправления.
            new SimpleUrlAuthenticationSuccessHandler(ADMIN_PAGE);// когда зашел admin вернет ADMIN_PAGE

    @Override // Аннотация, указывающая на переопределение метода.
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, // Реализация метода onAuthenticationSuccess, который вызывается при успешной аутентификации.
                                        Authentication authentication) throws IOException, ServletException {
        // поведение, если пользователь или админ смог зайти на сайт
        // берет роль и если user - то возвращает кейс для user
        // берет роль и если admin - то возвращает кейс для admin
        // берет роль и если другая роль - то возвращает ошибку
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); // Получение коллекции ролей (GrantedAuthority) пользователя из объекта аутентификации.
        for (final GrantedAuthority grantedAuthority : authorities) { // Итерация по коллекции ролей пользователя.
            String authorityName = grantedAuthority.getAuthority();
            switch (authorityName) { // Получение имени роли.
                case "ROLE_USER": // В случае роли "ROLE_USER" вызывается обработчик успешной аутентификации пользователя.
                    userSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                    return;
                case "ROLE_ADMIN": // В случае роли "ROLE_ADMIN" вызывается обработчик успешной аутентификации администратора.
                    adminSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                    return;
            }
        }
        throw new IllegalStateException(); // В случае, если роль не соответствует ни "ROLE_USER", ни "ROLE_ADMIN", выбрасывается исключение IllegalStateException.
    }
}
