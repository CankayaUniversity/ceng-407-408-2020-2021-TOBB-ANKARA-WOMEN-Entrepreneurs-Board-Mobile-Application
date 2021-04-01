package com.kgk.web;

import com.kgk.model.admin.RegisterForm;
import com.kgk.repository.RegisterFormRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;

import javax.validation.Valid;

@Controller("/api/register-form")
public class RegisterFormController {

    private final RegisterFormRepository registerFormRepository;

    public RegisterFormController(RegisterFormRepository registerFormRepository) {
        this.registerFormRepository = registerFormRepository;
    }

    @Post
    public void save(@Valid @Body RegisterForm registerForm) {
        registerFormRepository.saveRegisterForm(registerForm);
    }
}
