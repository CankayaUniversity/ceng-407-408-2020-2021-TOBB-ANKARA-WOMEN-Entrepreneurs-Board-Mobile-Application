package com.kgk.repository.admin;

import com.kgk.model.RegisterForm;
import com.kgk.model.User;

import java.util.Collection;

public interface MembershipRepository {

    Collection<RegisterForm> listAllUnapprovedRegisterForms(); //lists all unapproved register forms

    void approveRegisterForm(String registerId, RegisterForm registerForm); //creates a new user

    void declineRegisterForm(String registerId, String city); //deletes the register form

}
