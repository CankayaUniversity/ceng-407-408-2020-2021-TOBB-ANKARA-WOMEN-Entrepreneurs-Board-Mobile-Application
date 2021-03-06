package com.kgk.model.admin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Introspected
@DynamoDBTable(tableName = "RegisterForms")
public class RegisterForm {

    @NonNull
    private String registerId; //hash key

    @NonNull
    private String city;   //gsi hash key

    @NonNull
    @NotBlank
    private String firstName;

    @NonNull
    @NotBlank
    private String lastName;

    @NonNull
    @NotBlank
    @Email
    private String email;

    @NonNull
    @NotBlank
    private String password;

    @NonNull
    @NotBlank
    private String phone;

    @NonNull
    @NotBlank
    private String tobbRegisterId;

    @NonNull
    private String occupation;

    @NonNull
    private Long registerDate; //gsi range key

    @NonNull
    private String approved;

    @NonNull
    private String roleId;

    // Partition key
    @DynamoDBHashKey(attributeName = "registerId")
    @NonNull
    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(@NonNull String registerId) {
        this.registerId = registerId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "registerFormsByCity", attributeName = "city")
    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @DynamoDBAttribute(attributeName = "firstName")
    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute(attributeName = "lastName")
    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBAttribute(attributeName = "email")
    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = "phone")
    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @DynamoDBAttribute(attributeName = "password")
    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @DynamoDBAttribute(attributeName = "tobbRegisterId")
    @NonNull
    public String getTobbRegisterId() {
        return tobbRegisterId;
    }

    public void setTobbRegisterId(@NonNull String tobbRegisterId) {
        this.tobbRegisterId = tobbRegisterId;
    }

    @DynamoDBAttribute(attributeName = "occupation")
    @NonNull
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(@NonNull String occupation) {
        this.occupation = occupation;
    }

    @DynamoDBAttribute(attributeName = "registerDate")
    @NonNull
    public Long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(@NonNull Long registerDate) {
        this.registerDate = registerDate;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "registerFormsByCity", attributeName = "approved")
    @NonNull
    public String isApproved() {
        return approved;
    }

    public void setApproved(@NonNull String approved) {
        this.approved = approved;
    }

    @DynamoDBAttribute(attributeName = "roleId")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
