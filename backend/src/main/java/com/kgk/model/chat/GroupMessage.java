package com.kgk.model.chat;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
@DynamoDBTable(tableName = "GroupMessages")
public class GroupMessage {

    @NonNull
    private String messageId; //hash key

    @NonNull
    private String groupId; //gsi - hash key

    @NonNull
    private Long sendAt; //gsi - range key

    @NonNull
    private String sentBy; //userId

    @NonNull
    @NotBlank
    private String message;

    public GroupMessage() {}

    // Partition key
    @DynamoDBHashKey(attributeName = "messageId")
    @NonNull
    public String getMessageId() { return messageId; }

    public void setMessageId(@NonNull String messageId) { this.messageId = messageId; }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "messagesByGroupId", attributeName = "groupId")
    @NonNull
    public String getGroupId() { return groupId; }

    public void setGroupId(@NonNull String groupId) { this.groupId = groupId; }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "messagesByGroupId", attributeName = "sendAt")
    @NonNull
    public Long getSendAt() { return sendAt; }

    public void setSendAt(@NonNull Long sendAt) { this.sendAt = sendAt; }

    @DynamoDBAttribute(attributeName = "sentBy")
    @NonNull
    public String getSentBy() { return sentBy; }

    public void setSentBy(@NonNull String sentBy) { this.sentBy = sentBy; }

    @DynamoDBAttribute(attributeName = "message")
    @NonNull
    public String getMessage() { return message; }

    public void setMessage(@NonNull String message) { this.message = message; }

}
