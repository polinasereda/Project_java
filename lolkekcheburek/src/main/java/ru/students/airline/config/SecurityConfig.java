package ru.students.airline.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.students.airline.security.CustomAuthProvider;

@EnableWebSecurity // Включает защиту веб-безопасности для проекта
@RequiredArgsConstructor // Lombok аннотация для создания конструктора с обязательными аргументами
@EnableGlobalMethodSecurity(prePostEnabled = true) // Включает глобальную методную безопасность
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandler successHandler; // Обработчик успешной аутентификации
    private final CustomAuthProvider provider; // Поставщик пользовательской аутентификации

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Отключает CSRF защиту
            .authorizeRequests() // Начинает создание правил авторизации
            .antMatchers("/admin, /upload").hasRole("ADMIN") // Определяет доступ только для пользователей с ролью ADMIN
            .antMatchers("/auth/login", "/auth/registration", "/error").permitAll() // Позволяет всем доступ к указанным страницам
            .anyRequest().authenticated() // Требует аутентификации для всех остальных запросов
            .and()
            .formLogin() // Включает форму логина
            .loginPage("/auth/login") // Устанавливает страницу логина
            .loginProcessingUrl("/process_login") // Устанавливает URL для обработки формы логина
            .successHandler(successHandler) // Назначает обработчик успешной аутентификации
            .failureUrl("/auth/login?error") // Устанавливает URL для случая ошибки входа
            .and()
            .logout() // Настраивает выход из системы
            .logoutUrl("/logout") // Устанавливает URL для выхода из системы
            .logoutSuccessUrl("/auth/login"); // Устанавливает URL при успешном выходе
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider); // Настраивает поставщика аутентификации
    }
}
