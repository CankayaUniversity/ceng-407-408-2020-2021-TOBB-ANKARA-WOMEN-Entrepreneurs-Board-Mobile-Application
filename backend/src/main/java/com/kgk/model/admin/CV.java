package com.kgk.model.admin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

@Introspected
@DynamoDBTable(tableName = "CVs")
public class CV {

    @NonNull
    private String cvId;

    @NonNull
    private String uploadedBy; //userId

    @NonNull
    private String cvFile;

    @NonNull
    private Long uploadDate;

    private String roleId;

    private boolean approved;

    public CV(){}

    // Partition key
    @DynamoDBHashKey(attributeName = "cvId")
    @NonNull
    public String getCvId() {
        return cvId;
    }

    public void setCvId(@NonNull String cvId) {
        this.cvId = cvId;
    }

    @DynamoDBRangeKey(attributeName = "uploadedBy")
    @NonNull
    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(@NonNull String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @DynamoDBAttribute(attributeName = "cvFile")
    @NonNull
    public String getCvFile() {
        return cvFile;
    }

    public void setCvFile(@NonNull String cvFile) {
        this.cvFile = cvFile;
    }

    @DynamoDBAttribute(attributeName = "roleId")
    @NonNull
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @DynamoDBAttribute(attributeName = "uploadDate")
    @NonNull
    public Long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(@NonNull Long uploadDate) {
        this.uploadDate = uploadDate;
    }

    @DynamoDBAttribute(attributeName = "approved")
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

}
