package com.kgk.model.admin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

@Introspected
@DynamoDBTable(tableName = "Advertisements")
public class Advertisement {

    @NonNull
    private Integer advId; //hash key

    @NonNull
    private String advHeader;

    @NonNull
    private String advBody;

    @NonNull
    private String requestby; //range key

    @NonNull
    private Long requestDate;

    private String roleId;

    private boolean approved;

    public Advertisement(){}

    // Partition key
    @DynamoDBHashKey(attributeName = "advId")
    @NonNull
    public Integer getAdvId() {
        return advId;
    }

    public void setAdvId(@NonNull Integer advId) {
        this.advId = advId;
    }

    @DynamoDBAttribute(attributeName = "advHeader")
    @NonNull
    public String getAdvHeader() {
        return advHeader;
    }

    public void setAdvHeader(@NonNull String advHeader) {
        this.advHeader = advHeader;
    }

    @DynamoDBAttribute(attributeName = "advBody")
    @NonNull
    public String getAdvBody() {
        return advBody;
    }

    public void setAdvBody(@NonNull String advBody) {
        this.advBody = advBody;
    }

    @DynamoDBRangeKey(attributeName = "requestBy")
    @NonNull
    public String getRequestby() {
        return requestby;
    }

    public void setRequestby(@NonNull String requestby) {
        this.requestby = requestby;
    }

    @DynamoDBAttribute(attributeName = "requestDate")
    @NonNull
    public Long getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(@NonNull Long requestDate) {
        this.requestDate = requestDate;
    }

    @DynamoDBAttribute(attributeName = "roleId")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @DynamoDBAttribute(attributeName = "approved")
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

}
