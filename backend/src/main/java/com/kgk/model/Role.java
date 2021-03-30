package com.kgk.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

@Introspected
@DynamoDBTable(tableName = "Roles")
public class Role {

    @NonNull
    private String roleName; //hash key

    @NonNull
    private String roleId; //range key

    // Partition key
    @DynamoDBHashKey(attributeName = "roleName")
    @NonNull
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NonNull String roleName) {
        this.roleName = roleName;
    }

    @DynamoDBRangeKey(attributeName = "roleId")
    @NonNull
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(@NonNull String roleId) {
        this.roleId = roleId;
    }

}
