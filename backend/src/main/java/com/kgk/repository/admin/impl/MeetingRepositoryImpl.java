package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgk.model.DeletedItem;
import com.kgk.model.admin.Meeting;
import com.kgk.model.user.User;
import com.kgk.repository.admin.MeetingRepository;
import com.kgk.repository.user.UserRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Singleton
public class MeetingRepositoryImpl implements MeetingRepository {

    private static final String TABLE_NAME = "Meetings";

    private static final String GSI_NAME = "meetingsByCity";

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final UserRepository userRepository;

    public MeetingRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config,
                                 UserRepository userRepository) {
        this.mapper = mapper;
        this.config = config;
        this.userRepository = userRepository;
    }

    @Override
    public List<Meeting> listAllMeetings(String userId) {
        User currentUser = userRepository.findUserById(userId);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":city", new AttributeValue().withS(currentUser.getCity()));
        eav.put(":updatedAt", new AttributeValue().withN(String.valueOf(System.currentTimeMillis())));

        DynamoDBQueryExpression<Meeting> queryExpression = new DynamoDBQueryExpression<Meeting>()
                .withIndexName(GSI_NAME)
                .withKeyConditionExpression("city = :city and updatedAt < :updatedAt")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(Meeting.class, queryExpression);
    }

    @Override
    public Meeting findMeetingByMeetingId(String meetingId) {
        return mapper.load(Meeting.class, meetingId, config);
    }

    @Override
    public Meeting saveMeeting(Meeting meeting) {
        meeting.setMeetingId(UUID.randomUUID().toString());
        meeting.setPublishDate(System.currentTimeMillis());
        meeting.setUpdatedAt(meeting.getPublishDate());

        mapper.save(meeting);
        System.out.println("[MEETING REPO] Meeting is saved");

        return meeting;
    }

    @Override
    public Meeting updateMeeting(String meetingId, Meeting meeting) {
        Meeting retrievedMeeting = findMeetingByMeetingId(meetingId);
        retrievedMeeting.setCity(meeting.getCity());
        retrievedMeeting.setMeetingUrl(meeting.getMeetingUrl());
        retrievedMeeting.setMeetingPlace(meeting.getMeetingPlace());
        retrievedMeeting.setStartTime(meeting.getStartTime());
        retrievedMeeting.setEndTime(meeting.getEndTime());
        retrievedMeeting.setUpdatedAt(System.currentTimeMillis());

        mapper.save(retrievedMeeting);
        System.out.println("[MEETING REPO] Meeting is updated");

        return retrievedMeeting;
    }

    @Override
    public void deleteMeeting(String meetingId) {
        Meeting meeting = findMeetingByMeetingId(meetingId);
        DeletedItem deletedMeeting = new DeletedItem();
        deletedMeeting.setDeletedTime(System.currentTimeMillis());
        deletedMeeting.setWhichTable(TABLE_NAME);
        deletedMeeting.setOriginalId(meeting.getMeetingId());

        try {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(meeting);
            deletedMeeting.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapper.save(deletedMeeting);
        System.out.println("[MEETING REPO] Deleted meeting is saved to DeletedItems table");

        mapper.delete(meeting);
        System.out.println("[MEETING REPO] Meeting is deleted");
    }

}
