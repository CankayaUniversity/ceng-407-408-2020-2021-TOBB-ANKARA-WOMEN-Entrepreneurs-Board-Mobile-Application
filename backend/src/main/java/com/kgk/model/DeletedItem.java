package com.kgk.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Introspected
@DynamoDBTable(tableName = "DeletedItems")
public class DeletedItem {

    @NonNull
    private String whichTable;  //hash key

    @NonNull
    private Long deletedTime; //range key

    @NonNull
    private String originalId; //gsi range key

    @NonNull
    private String json;

    // Partition key
    @DynamoDBHashKey(attributeName = "whichTable")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "itemsByOriginalId", attributeName = "whichTable")
    @NonNull
    public String getWhichTable() {
        return whichTable;
    }

    public void setWhichTable(@NonNull String whichTable) {
        this.whichTable = whichTable;
    }

    @DynamoDBRangeKey(attributeName = "deletedTime")
    @NonNull
    public Long getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(@NonNull Long deletedTime) {
        this.deletedTime = deletedTime;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "itemsByOriginalId", attributeName = "originalId")
    @NonNull
    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(@NonNull String originalId) {
        this.originalId = originalId;
    }

    @DynamoDBAttribute(attributeName = "json")
    @NonNull
    public String getJson() {
        return json;
    }

    public void setJson(@NonNull String json) {
        this.json = json;
    }

}