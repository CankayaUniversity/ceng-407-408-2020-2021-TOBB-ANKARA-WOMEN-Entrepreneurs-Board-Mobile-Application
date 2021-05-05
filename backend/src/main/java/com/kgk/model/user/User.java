package com.kgk.model.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.util.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Introspected
@DynamoDBTable(tableName = "Users")
public class User {

    @NonNull
    private String userId; //hash key

    @NonNull
    @NotBlank
    private String city;   //gsi - hash key

    @NonNull
    private String roleId; //gsi - range key

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
    private String tobbRegisterId;

    @NonNull
    @NotBlank
    private String phone;

    @NonNull
    private String occupation;

    private String photo;

    private Long birthDate;

    private String description;

    private List<Catalog> catalogList = new ArrayList<>();

    public User() {}

    // Partition key
    @DynamoDBHashKey(attributeName = "userId")
    @NonNull
    public String getUserId() {
      return userId;
    }

    public void setUserId(@NonNull String userId) {
      this.userId = userId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {"usersByCity", "usersByCityAndRoleId"}, attributeName = "city")
    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "usersByCityAndRoleId", attributeName = "roleId")
    @NonNull
    public String getRoleId() {
      return roleId;
    }

    public void setRoleId(@NonNull String roleId) {
      this.roleId = roleId;
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

    @DynamoDBAttribute(attributeName = "password")
    @NonNull
    public String getPassword() {
      return password;
    }

    public void setPassword(@NonNull String password) { this.password = password; }

    @DynamoDBAttribute(attributeName = "photo")
    public String getPhoto() {
      return photo;
    }

    public void setPhoto(String photo) {
      this.photo = photo;
    }

    @DynamoDBAttribute(attributeName = "birthDate")
    public Long getBirthDate() {
      return birthDate;
    }

    public void setBirthDate(Long birthDate) {
      this.birthDate = birthDate;
    }

    @DynamoDBAttribute(attributeName = "occupation")
    @NonNull
    public String getOccupation() {
      return occupation;
    }

    public void setOccupation(@NonNull String occupation) {
      this.occupation = occupation;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "phone")
    @NonNull
    public String getPhone() {
      return phone;
    }

    public void setPhone(@NonNull String phone) {
      this.phone = phone;
    }

    @DynamoDBAttribute(attributeName = "tobbRegisterId")
    @NonNull
    public String getTobbRegisterId() {
        return tobbRegisterId;
    }

    public void setTobbRegisterId(@NonNull String tobbRegisterId) {
        this.tobbRegisterId = tobbRegisterId;
    }

    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    public void copyFrom(User user) {
        //this.setUserId(user.getUserId());
        this.setCity(user.city);
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setTobbRegisterId(user.getTobbRegisterId());
        this.setOccupation(user.getOccupation());
        this.setPhone(user.getPhone());

        if (StringUtils.isNotEmpty(user.getRoleId()))
            this.setRoleId(user.getRoleId());

        if (StringUtils.isNotEmpty(user.getPhoto()))
            this.setPhone(user.getPhone());

        if (user.getBirthDate() != null)
            this.setBirthDate(user.getBirthDate());

        if (StringUtils.isNotEmpty(user.getDescription()))
            this.setDescription(user.getDescription());

    }
    
}