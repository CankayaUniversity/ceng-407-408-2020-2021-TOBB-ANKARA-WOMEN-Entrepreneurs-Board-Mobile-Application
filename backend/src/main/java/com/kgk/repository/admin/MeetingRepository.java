package com.kgk.repository.admin;

import com.kgk.model.admin.Meeting;

import java.util.List;

public interface MeetingRepository {

    List<Meeting> listAllMeetings(); //lists all meetings that the meeting variable is true

    Meeting findMeetingByMeetingId(String newsId);

    Meeting saveMeeting(Meeting meeting);

    Meeting updateMeeting(String meetingId, Meeting meeting);

    void deleteMeeting(String meetingId);

}
