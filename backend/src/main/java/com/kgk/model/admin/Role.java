package com.kgk.model.admin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

@Introspected
@DynamoDBTable(tableName = "Roles")
public class Role {

    @NonNull
    private String roleName; //hash key

    // Partition key
    @DynamoDBHashKey(attributeName = "roleName")
    @NonNull
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NonNull String roleName) {
        this.roleName = roleName;
    }

}
