package com.example.sweater.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    //Разрешаем доступ только к главной
                    .antMatchers("/", "/registration").permitAll()
                    //Для остальных требуем авторизацию
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    //Мапинг для страницы авторизации
                    .loginPage("/login")
                    //Разрешаем всем пользоваться этим
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                //Нужно что бы менеджер мог ходить в бд и искать пользователей и их роли
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                //Система ищет пользователя по имени, порядок и набор полей определяются системой
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                //Получить список пользователей с их ролями
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where username=?");

    }
}
