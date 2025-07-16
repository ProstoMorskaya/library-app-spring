package com.kmdev.springcourse.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateDto {

    private int id;

    @NotEmpty(message = "Инициалы не должны быть пустыми")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+",
            message = "ФИО должны быть в следующем формате: Сидоров Андрей Андреевич")
    private String initials;

    @NotNull(message = "Дата рождения не может быть null")
    private String dateOfBirth;

    @NotEmpty(message = "Пароль не должен быть пустым")
    private String password;

    private String role;
}
