package com.kmdev.springcourse.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // Получаем текущего пользователя (UserDetails)
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        int userId = userDetails.getPerson().getId();

        // Редирект на персональную страницу
        response.sendRedirect("/people/" + userId);
    }
}