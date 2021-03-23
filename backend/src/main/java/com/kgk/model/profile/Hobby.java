package com.kgk.model.profile;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
@DynamoDBTable(tableName = "Hobbies")
public class Hobby {

    @NonNull
    private String hobbyId; //hash key

    @NonNull
    private String userId; //range key

    @NonNull
    @NotBlank
    private String hobbyName;

    @NonNull
    @NotBlank
    private String hobbyDesc;

    // Partition key
    @DynamoDBHashKey(attributeName = "hobbyId")
    @DynamoDBAutoGeneratedKey
    @NonNull
    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(@NonNull String hobbyId) {
        this.hobbyId = hobbyId;
    }

    @DynamoDBRangeKey(attributeName = "userId")
    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "hobbyName")
    @NonNull
    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(@NonNull String hobbyName) {
        this.hobbyName = hobbyName;
    }

    @DynamoDBAttribute(attributeName = "hobbyDesc")
    @NonNull
    public String getHobbyDesc() {
        return hobbyDesc;
    }

    public void setHobbyDesc(@NonNull String hobbyDesc) {
        this.hobbyDesc = hobbyDesc;
    }

}