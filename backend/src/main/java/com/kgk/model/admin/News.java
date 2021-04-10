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

    private String newsTitle;

    private String newsBody;

    @NonNull
    private String meeting; //gsi - hash key

    private Long startTime;

    private Long endTime;

    private String meetingPlace;

    private String meetingUrl;

    @NonNull
    private Long publishDate;

    @NonNull
    private Long updatedAt;  //gsi - range key

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

    @DynamoDBAttribute(attributeName = "newsTitle")
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @DynamoDBAttribute(attributeName = "newsBody")
    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }

    @DynamoDBAttribute(attributeName = "startTime")
    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @DynamoDBAttribute(attributeName = "endTime")
    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @DynamoDBAttribute(attributeName = "meetingPlace")
    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    @DynamoDBAttribute(attributeName = "meetingUrl")
    public String getMeetingUrl() {
        return meetingUrl;
    }

    public void setMeetingUrl(String meetingUrl) {
        this.meetingUrl = meetingUrl;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "newsByPublishDate", attributeName = "meeting")
    @NonNull
    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(@NonNull String meeting) {
        this.meeting = meeting;
    }

    @DynamoDBAttribute(attributeName = "publishDate")
    @NonNull
    public Long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(@NonNull Long publishDate) {
        this.publishDate = publishDate;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "newsByPublishDate", attributeName = "updatedAt")
    @NonNull
    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@NonNull Long updatedAt) {
        this.updatedAt = updatedAt;
    }

}
