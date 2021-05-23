package com.kgk.model.admin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

@Introspected
@DynamoDBTable(tableName = "Meetings")
public class Meeting {

    @NonNull
    private String meetingId; //hash key

    @NonNull
    public String city;     //gsi - hash key

    @NonNull
    private Long updatedAt;  //gsi - range key

    @NonNull
    private Long startTime;

    @NonNull
    private Long endTime;

    @NonNull
    private String meetingPlace;

    @NonNull
    private String meetingUrl;

    @NonNull
    private Long publishDate;

    // Partition key
    @DynamoDBHashKey(attributeName = "meetingId")
    @NonNull
    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(@NonNull String meetingId) {
        this.meetingId = meetingId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "meetingsByCity", attributeName = "city")
    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "meetingsByCity", attributeName = "updatedAt")
    @NonNull
    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@NonNull Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @DynamoDBAttribute(attributeName = "startTime")
    @NonNull
    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(@NonNull Long startTime) {
        this.startTime = startTime;
    }

    @DynamoDBAttribute(attributeName = "endTime")
    @NonNull
    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(@NonNull Long endTime) {
        this.endTime = endTime;
    }

    @DynamoDBAttribute(attributeName = "meetingPlace")
    @NonNull
    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(@NonNull String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    @DynamoDBAttribute(attributeName = "meetingUrl")
    @NonNull
    public String getMeetingUrl() {
        return meetingUrl;
    }

    public void setMeetingUrl(@NonNull String meetingUrl) {
        this.meetingUrl = meetingUrl;
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
