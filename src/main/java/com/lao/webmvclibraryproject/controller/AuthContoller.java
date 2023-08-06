package com.lao.webmvclibraryproject.controller;

import com.lao.webmvclibraryproject.model.User;
import com.lao.webmvclibraryproject.service.RegistrationService;
import com.lao.webmvclibraryproject.service.UserService;
import com.lao.webmvclibraryproject.utils.UserValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthContoller {

    private final UserService userService;
    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@Valid @ModelAttribute("user") User user,
                                  BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        registrationService.register(user);
        return "redirect:/auth/login";
    }

    @GetMapping("/edit")
    public String registrationPage(@ModelAttribute("user") User user, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("user", userService.findUserByName(currentPrincipalName).get());
        return "auth/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "auth/edit";
        User userToBeUpdated = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        userToBeUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setYear(user.getYear());
        userService.update(userToBeUpdated.getId(), userToBeUpdated);
        return "redirect:/books";
    }
}
