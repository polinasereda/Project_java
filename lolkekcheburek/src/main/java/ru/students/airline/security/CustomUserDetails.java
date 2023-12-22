package ru.students.airline.security; // Указывает на принадлежность класса к указанному пакету.

import lombok.Getter; // Импорт аннотации Getter из библиотеки Lombok для автоматической генерации методов getter.
import lombok.RequiredArgsConstructor; // Импорт аннотации RequiredArgsConstructor из библиотеки Lombok для автоматической генерации конструктора, принимающего все final поля класса.
import org.springframework.security.core.GrantedAuthority; // Импорт интерфейса GrantedAuthority из Spring Security, представляющего права доступа пользователя.
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Импорт класса SimpleGrantedAuthority из Spring Security, представляющего простую реализацию интерфейса GrantedAuthority.
import org.springframework.security.core.userdetails.UserDetails; // Импорт интерфейса UserDetails из Spring Security, представляющего основные сведения о пользователе.
import ru.students.airline.persistence.entity.User; // Импорт класса User из пакета 

import java.util.Collection; // Импорт интерфейса Collection из пакета java.util, представляющего коллекцию объектов.
import java.util.List;// Импорт класса List из пакета java.util, представляющего список объектов.

@RequiredArgsConstructor // Аннотация Lombok, указывающая на необходимость создания конструктора с аргументами для всех final полей класса.
public class CustomUserDetails implements UserDetails { // Объявление класса CustomUserDetails, который реализует интерфейс UserDetails.
    // Нужен, чтобы смотреть детали пользователя. При помощи интерфейса UserDetails
    @Getter // Аннотация Lombok, генерирующая методы getter для всех полей класса.
    private final User user; // Объявление поля user типа User, которое представляет пользователя.

    @Override // Аннотация, указывающая на переопределение метода.
    public Collection<? extends GrantedAuthority> getAuthorities() {// возвращает все роли пользователя
        return List.of(new SimpleGrantedAuthority(user.getRole())); // Реализация метода getAuthorities, возвращающего коллекцию ролей пользователя в виде GrantedAuthority.
    } // Возвращение списка с единственной ролью пользователя в виде SimpleGrantedAuthority.

    @Override
    public String getPassword() { // Реализация метода getPassword, возвращающего пароль пользователя.
        return this.user.getPassword(); // 
    }

    @Override
    public String getUsername() {
        return this.user.getUsername(); // Реализация метода getUsername, возвращающего имя пользователя.
    }

    @Override
    public boolean isAccountNonExpired() { // Реализация метода isAccountNonExpired, возвращающего true (счет не истек).
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // Реализация метода isAccountNonLocked, возвращающего true (счет не заблокирован).
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // Реализация метода isCredentialsNonExpired, возвращающего true (данные аутентификации не истекли).
        return true;
    }

    @Override
    public boolean isEnabled() { // Реализация метода isEnabled, возвращающего true (аккаунт пользователя включен).
        return true;
    }

}
