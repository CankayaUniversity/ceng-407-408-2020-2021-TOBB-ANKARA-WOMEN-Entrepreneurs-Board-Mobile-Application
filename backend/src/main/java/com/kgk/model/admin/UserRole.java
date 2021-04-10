package com.kgk.model.admin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

@Introspected
@DynamoDBTable(tableName = "UserRoles")
public class UserRole {

    @NonNull
    private String userId; //hash key

    @NonNull
    private String city; //gsi - hash key

    @NonNull
    private String roleId; //gsi - range key

    // Partition key
    @DynamoDBHashKey(attributeName = "userId")
    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {"userRolesByCity", "userRolesByCityAndRoleId"}, attributeName = "city")
    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "userRolesByCityAndRoleId", attributeName = "roleId")
    @NonNull
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(@NonNull String roleId) {
        this.roleId = roleId;
    }

}
