package com.kgk.repository.admin;

import com.kgk.model.admin.RegisterForm;

import java.util.Collection;

public interface RegisterFormRepository {

    Collection<RegisterForm> listAllRegisterForms();

    Collection<RegisterForm> listAllAcceptedRegisterForms();

    RegisterForm findRegisterFormById(String registerId);

    RegisterForm saveRegisterForm(RegisterForm registerForm);

    RegisterForm updateRegisterForm(RegisterForm registerForm);

    void deleteRegisterForm(String registerId);

}
