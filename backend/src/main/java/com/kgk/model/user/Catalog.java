package com.kgk.model.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
@DynamoDBTable(tableName = "Catalogs")
public class Catalog {

    @NonNull
    private String userId; //hash key

    @NonNull
    private String catalogId;  //range key

    @NonNull
    @NotBlank
    private String catalogName;

    @NonNull
    private String catalogUrl;

    @NonNull
    @NotBlank
    private String keywords;

    public Catalog() {}

    // Partition key
    @DynamoDBHashKey(attributeName = "userId")
    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "catalogId")
    @NonNull
    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(@NonNull String catalogId) {
        this.catalogId = catalogId;
    }

    @DynamoDBAttribute(attributeName = "catalogName")
    @NonNull
    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(@NonNull String catalogName) {
        this.catalogName = catalogName;
    }

    @DynamoDBAttribute(attributeName = "catalogUrl")
    @NonNull
    public String getCatalogUrl() {
        return catalogUrl;
    }

    public void setCatalogUrl(@NonNull String catalogUrl) {
        this.catalogUrl = catalogUrl;
    }

    @DynamoDBAttribute(attributeName = "keywords")
    @NonNull
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(@NonNull String keywords) {
        this.keywords = keywords;
    }

}
