package com.kmdev.springcourse.controllers;

import com.kmdev.springcourse.dto.PersonCreateDto;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.services.RegistrationService;
import com.kmdev.springcourse.util.PeopleCreateValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final PeopleCreateValidator peopleCreateValidator;
    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid PersonCreateDto personCreateDto,
                                      BindingResult bindingResult) {
        peopleCreateValidator.validate(personCreateDto, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors: {}", bindingResult.getAllErrors());
            return "auth/registration";
        }

        registrationService.register(personCreateDto);
        return "redirect:/auth/login";
    }
}
