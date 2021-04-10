package com.kgk.repository.admin;

import com.kgk.model.admin.RegisterForm;

import java.util.List;

public interface MembershipRepository {

    List<RegisterForm> listAllUnapprovedRegisterForms(); //lists all unapproved register forms

    RegisterForm findRegisterFormById(String registerFormId); //returns a specific register form

    RegisterForm approveRegisterForm(String registerId, RegisterForm registerForm); //creates a new user

    void declineRegisterForm(String registerId); //deletes the register form

}
