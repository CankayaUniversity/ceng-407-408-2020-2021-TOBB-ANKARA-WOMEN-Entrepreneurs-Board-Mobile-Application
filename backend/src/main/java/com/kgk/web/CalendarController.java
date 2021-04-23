package com.kgk.web;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.kgk.model.admin.Meeting;
import com.kgk.repository.admin.MeetingRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;
import javax.validation.Valid;
import java.util.List;

@Controller("/api/calendar")
public class CalendarController {

    private final MeetingRepository meetingRepository;

    public CalendarController(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Get
    public List<Meeting> listAllMeetings(/*AwsProxyRequest awsRequest*/) {
        return meetingRepository.listAllMeetings(/*awsRequest*/);
    }

    @Get("/{meetingId}")
    public Meeting findMeetingByMeetingId(@PathVariable("meetingId") String meetingId) {
        return meetingRepository.findMeetingByMeetingId(meetingId);
    }

    @Post
    public Meeting saveMeeting(@Valid @Body Meeting meeting) {
        return meetingRepository.saveMeeting(meeting);
    }

    @Put("/{meetingId}")
    public Meeting updateMeeting(@PathVariable("meetingId") String meetingId, @Valid @Body Meeting meeting) {
        return meetingRepository.updateMeeting(meetingId, meeting);
    }

    @Delete("/{meetingId}")
    public void deleteMeeting(@PathVariable("meetingId") String meetingId){
        meetingRepository.deleteMeeting(meetingId);
    }

}
