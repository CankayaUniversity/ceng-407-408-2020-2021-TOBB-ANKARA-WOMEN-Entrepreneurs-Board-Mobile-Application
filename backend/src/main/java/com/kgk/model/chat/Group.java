package com.kgk.model.chat;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
@DynamoDBTable(tableName = "Groups")
public class Group {

    @NonNull
    private String groupId; //hash key

    @NonNull
    private String city; //gsi range key

    @NonNull
    private String createdBy; //userId - gsi hash key

    @NonNull
    private Long createdAt;

    @NonNull
    @NotBlank
    private String groupName;

    private String groupDesc;

    public Group() {}

    // Partition key
    @DynamoDBHashKey(attributeName = "groupId")
    @NonNull
    public String getGroupId() { return groupId; }

    public void setGroupId(String groupId) { this.groupId = groupId; }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "groupsByCreatedBy", attributeName = "city")
    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "groupsByCreatedBy", attributeName = "createdBy")
    @NonNull
    public String getCreatedBy() { return createdBy; }

    public void setCreatedBy(@NonNull String createdBy) { this.createdBy = createdBy; }

    @DynamoDBAttribute(attributeName = "createdAt")
    @NonNull
    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Long createdAt) {
        this.createdAt = createdAt;
    }

    @DynamoDBAttribute(attributeName = "groupName")
    @NonNull
    public String getGroupName() { return groupName; }

    public void setGroupName(@NonNull String groupName) { this.groupName = groupName; }

    @DynamoDBAttribute(attributeName = "groupDesc")
    public String getGroupDesc() { return groupDesc; }

    public void setGroupDesc(String groupDesc) { this.groupDesc = groupDesc; }

}
