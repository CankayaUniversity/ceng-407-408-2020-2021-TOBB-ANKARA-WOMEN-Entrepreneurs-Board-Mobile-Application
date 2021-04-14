package com.kgk.model.chat;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
@DynamoDBTable(tableName = "GroupMembers")
public class GroupMessage {

    @NonNull
    private String groupId; //hash key

    @NonNull
    private String messageId; //range key

    @NonNull
    private Long sendAt; //local index range key

    @NonNull
    private String sentBy; //userId

    @NonNull
    @NotBlank
    private String message;

    public GroupMessage() {}

    // Partition key
    @DynamoDBHashKey(attributeName = "groupId")
    @NonNull
    public String getGroupId() { return groupId; }

    public void setGroupId(@NonNull String groupId) { this.groupId = groupId; }

    @DynamoDBRangeKey(attributeName = "messageId")
    @NonNull
    public String getMessageId() { return messageId; }

    public void setMessageId(@NonNull String messageId) { this.messageId = messageId; }

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "messagesByGroupId", attributeName = "sendAt")
    @NonNull
    public Long getSendAt() { return sendAt; }

    public void setSendAt(@NonNull Long sendAt) { this.sendAt = sendAt; }

    @DynamoDBAttribute(attributeName = "sentBy")
    @NonNull
    public String getSentBy() { return sentBy; }

    public void setSentBy(@NonNull String userId) { this.sentBy = sentBy; }

    @DynamoDBAttribute(attributeName = "message")
    @NonNull
    public String getMessage() { return message; }

    public void setMessage(@NonNull String message) { this.message = message; }

}
