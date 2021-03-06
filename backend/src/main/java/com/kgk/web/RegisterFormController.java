package com.kgk.web;

import com.kgk.model.admin.RegisterForm;
import com.kgk.repository.RegisterFormRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/api/register-form")
public class RegisterFormController {

    private final RegisterFormRepository registerFormRepository;

    public RegisterFormController(RegisterFormRepository registerFormRepository) {
        this.registerFormRepository = registerFormRepository;
    }

    @Post
    public RegisterForm save(@Valid @Body RegisterForm registerForm) {
        return registerFormRepository.saveRegisterForm(registerForm);
    }
}
