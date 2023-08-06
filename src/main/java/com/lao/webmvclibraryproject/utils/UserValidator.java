package com.lao.webmvclibraryproject.utils;

import com.lao.webmvclibraryproject.model.User;
import com.lao.webmvclibraryproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.findUserByEmail(user.getEmail()).isPresent())
            errors.rejectValue("email", "", "User with that email already exists");

    }
}
