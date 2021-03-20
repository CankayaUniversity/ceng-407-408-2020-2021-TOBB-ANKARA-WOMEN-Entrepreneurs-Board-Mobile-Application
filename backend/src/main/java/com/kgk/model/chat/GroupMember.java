package com.kgk.model.chat;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

@Introspected
@DynamoDBTable(tableName = "GroupMembers")
public class GroupMember {

    @NonNull
    private String userId; //hash key

    @NonNull
    private String groupId; //range key

    @NonNull
    private Long joinedAt;

    public GroupMember() {}

    // Partition key
    @DynamoDBHashKey(attributeName = "userId")
    @NonNull
    public String getUserId() { return userId; }

    public void setUserId(@NonNull String userId) { this.userId = userId; }

    @DynamoDBRangeKey(attributeName = "groupId")
    @NonNull
    public String getGroupId() { return groupId; }

    public void setGroupId(@NonNull String groupId) { this.groupId = groupId; }

    @DynamoDBAttribute(attributeName = "joinedAt")
    @NonNull
    public Long getJoinedAt() {return joinedAt; }

    public void setJoinedAt(@NonNull Long joinedAt) { this.joinedAt = joinedAt; }

}
