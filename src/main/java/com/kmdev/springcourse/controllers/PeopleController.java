package com.kmdev.springcourse.controllers;

import com.kmdev.springcourse.dto.PersonCreateDto;
import com.kmdev.springcourse.dto.PersonUpdateDto;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.models.Role;
import com.kmdev.springcourse.services.PeopleService;
import com.kmdev.springcourse.util.PeopleCreateValidator;
import com.kmdev.springcourse.util.PeopleUpdateValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;
    private final PeopleCreateValidator peopleCreateValidator;
    private final PeopleUpdateValidator peopleUpdateValidator;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.person.id or hasAuthority('ADMIN')")
    public String show(@PathVariable("id") int id, Model model) {
        var person = peopleService.findByIdWithBooks(id);

        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid PersonCreateDto personCreateDto,
                         BindingResult bindingResult) {
        peopleCreateValidator.validate(personCreateDto, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(personCreateDto);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("#id == authentication.principal.person.id or hasAuthority('ADMIN')")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.person.id or hasAuthority('ADMIN')")
    public String update(@ModelAttribute("person") @Valid PersonUpdateDto personUpdateDto, BindingResult bindingResult,
                         @PathVariable("id") int id, Authentication authentication) {
        personUpdateDto.setId(id);
        peopleUpdateValidator.validate(personUpdateDto, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(Role.ADMIN.name()));


        if (!isAdmin) {
            personUpdateDto.setRole(Role.USER.name());
        }

        peopleService.update(personUpdateDto);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
