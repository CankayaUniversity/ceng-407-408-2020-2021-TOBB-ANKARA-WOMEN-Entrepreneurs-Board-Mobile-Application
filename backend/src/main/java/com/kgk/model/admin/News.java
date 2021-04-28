package com.kgk.model.admin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

@Introspected
@DynamoDBTable(tableName = "News")
public class News {

    @NonNull
    private String newsId; //hash key

    @NonNull
    private String city; //gsi - hash key

    @NonNull
    private Long updatedAt;  //gsi - range key

    @NonNull
    private String newsTitle;

    @NonNull
    private String newsBody;

    @NonNull
    private Long publishDate;

    public News() {}

    // Partition key
    @DynamoDBHashKey(attributeName = "newsId")
    @NonNull
    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(@NonNull String newsId) {
        this.newsId= newsId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "newsByCity", attributeName = "city")
    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "newsByCity", attributeName = "updatedAt")
    @NonNull
    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@NonNull Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @DynamoDBAttribute(attributeName = "newsTitle")
    @NonNull
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(@NonNull String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @DynamoDBAttribute(attributeName = "newsBody")
    @NonNull
    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(@NonNull String newsBody) {
        this.newsBody = newsBody;
    }

    @DynamoDBAttribute(attributeName = "publishDate")
    @NonNull
    public Long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(@NonNull Long publishDate) {
        this.publishDate = publishDate;
    }

}
