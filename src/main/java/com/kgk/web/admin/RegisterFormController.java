package com.kgk.web.admin;

import com.kgk.model.admin.RegisterForm;
import com.kgk.repository.admin.RegisterFormRepository;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller("/register-form")
public class RegisterFormController {

    private final RegisterFormRepository registerFormRepository;

    public RegisterFormController(RegisterFormRepository registerFormRepository) {
        this.registerFormRepository = registerFormRepository;
    }

    @Post
    public RegisterForm save(@Valid @Body RegisterForm registerForm) {
        return registerFormRepository.saveRegisterForm(registerForm);
    }

    @Put
    public RegisterForm update(@Valid @Body RegisterForm registerForm) {
        return registerFormRepository.updateRegisterForm(registerForm);
    }

    @Delete
    public void delete(@Valid @Body RegisterForm registerForm){
        registerFormRepository.deleteRegisterForm(registerForm);
    }

    @Get
    public Collection<RegisterForm> listAll() {
        return registerFormRepository.listAllRegisterForms();
    }

    @Get("/approved")
    public Collection<RegisterForm> listAllApproved() {
        return registerFormRepository.listAllAcceptedRegisterForms();
    }
}
