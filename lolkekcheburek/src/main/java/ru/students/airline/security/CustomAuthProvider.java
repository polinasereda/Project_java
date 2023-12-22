// Указывает на принадлежность класса к указанному пакету
package ru.students.airline.security;
import lombok.RequiredArgsConstructor; // Импорт аннотации Lombok RequiredArgsConstructor, которая автоматически создает конструктор с аргументами для полей класса.
import org.springframework.security.authentication.AuthenticationProvider; // Импорт интерфейса AuthenticationProvider из Spring Security, который предоставляет абстракцию для поставщиков аутентификации.
import org.springframework.security.authentication.BadCredentialsException; // Импорт исключения BadCredentialsException из Spring Security, которое может быть выброшено в случае неправильных учетных данных.
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Импорт класса UsernamePasswordAuthenticationToken из Spring Security, который представляет собой токен аутентификации на основе имени пользователя и пароля.
import org.springframework.security.core.Authentication; // Импорт интерфейса Authentication из Spring Security, который представляет собой объект аутентификации.
import org.springframework.security.core.AuthenticationException; // Импорт исключения AuthenticationException из Spring Security, представляющего общее исключение аутентификации.
import org.springframework.security.core.context.SecurityContextHolder; // Импорт класса SecurityContextHolder из Spring Security, предоставляющего доступ к контексту безопасности.
import org.springframework.security.core.userdetails.UserDetails; // Импорт интерфейса UserDetails из Spring Security, предоставляющего необходимые детали о пользователе.
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Импорт исключения UsernameNotFoundException из Spring Security, которое может быть выброшено, если не удается найти пользователя по имени.
import org.springframework.stereotype.Component; // Импорт аннотации Component из Spring Framework, которая указывает, что этот класс является компонентом Spring и должен быть автоматически обнаружен и зарегистрирован в контексте Spring.
import ru.students.airline.persistence.entity.User; // Импорт класса User из пакета ru.students.airline.persistence.entity.
import ru.students.airline.services.CustomDetailsService; // Импорт сервиса CustomDetailsService из пакета ru.students.airline.services.
import ru.students.airline.services.RegistrationService; // Импорт сервиса RegistrationService из пакета ru.students.airline.services.

@Component // Аннотация, указывающая, что этот класс является компонентом Spring и должен быть управляемым контейнером Spring
@RequiredArgsConstructor // Аннотация Lombok, добавляющая конструктор с аргументами для всех полей класса.
public class CustomAuthProvider implements AuthenticationProvider {   // Объявление класса CustomAuthProvider, который реализует интерфейс AuthenticationProvider.
    //Пользовательский поставщик авторизации. При помощи интерфейса AuthenticationProvider

    private final CustomDetailsService detailsService; // Приватное поле для хранения экземпляра CustomDetailsService.
    private final RegistrationService registrationService; // Приватное поле для хранения экземпляра RegistrationService.

    @Override // Аннотация, указывающая на переопределение метода.
    public Authentication authenticate(Authentication authentication) throws AuthenticationException { // Реализация метода authenticate из интерфейса AuthenticationProvider. Здесь происходит процесс аутентификации пользователя.
        String username = authentication.getName(); // Получение имени пользователя из объекта аутентификации.
        String password = authentication.getCredentials().toString(); // Получение пароля пользователя из объекта аутентификации.

        if (username.isEmpty() || password.isEmpty()) // Проверка наличия имени пользователя и пароля, и выброс исключения 
            throw new BadCredentialsException("Incorrect password");

        UserDetails personDetails; // Объявление переменной personDetails для хранения деталей пользователя.

        try { // Попытка загрузить детали пользователя по имени. Если пользователь не найден, будет выполнен блок catch, в котором осуществляется регистрация нового пользователя.
            personDetails = detailsService.loadUserByUsername(username);
            // мы пытаемся найти информацию по имени, а имени нет, значит имя нужно создать
        } catch (UsernameNotFoundException ignore) {
            registrationService.register(new User(username, password, "ROLE_USER"));
            personDetails = detailsService.loadUserByUsername(username); // новые данные о пользователе, котого создали
        }

        if (!password.equals(personDetails.getPassword()))// смотрим на правильность пароля
            throw new BadCredentialsException("Incorrect password");

        return new UsernamePasswordAuthenticationToken(personDetails, password, personDetails.getAuthorities());
        // Token позволяет отслеживать активность пользователя на сайте
    }

    @Override // Аннотация, указывающая на переопределение метода.
    public boolean supports(Class<?> authentication) { // Реализация метода supports, который проверяет, поддерживает ли провайдер указанный тип аутентификации (UsernamePasswordAuthenticationToken).
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        // supports проверяет этот токен. Если он есть то все хорошо
    }

    public static String getAuthenticatedUsername() { // Объявление статического метода для получения имени аутентифицированного пользователя.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();// данные от чего имени ведутся запросы
        CustomUserDetails personDetails = (CustomUserDetails) authentication.getPrincipal();// берет детали о пользователи
        return personDetails.getUsername(); // Возвращение имени пользователя.
    }
}
