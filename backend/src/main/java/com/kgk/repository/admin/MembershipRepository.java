package com.kgk.repository.admin;

import com.kgk.model.admin.RegisterForm;

import java.util.List;

public interface MembershipRepository {

    List<RegisterForm> listAllUnapprovedRegisterForms(); //lists all unapproved register forms

    void approveRegisterForm(String registerId, RegisterForm registerForm); //creates a new user

    void declineRegisterForm(String registerId, String city); //deletes the register form

}
