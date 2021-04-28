package com.kgk.model.user;

import edu.umd.cs.findbugs.annotations.NonNull;

import javax.validation.constraints.NotBlank;

public class Password {

    @NonNull
    @NotBlank
    private String oldPassword;

    @NonNull
    @NotBlank
    private String newPassword;

    @NonNull
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(@NonNull String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @NonNull
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(@NonNull String newPassword) {
        this.newPassword = newPassword;
    }

}
