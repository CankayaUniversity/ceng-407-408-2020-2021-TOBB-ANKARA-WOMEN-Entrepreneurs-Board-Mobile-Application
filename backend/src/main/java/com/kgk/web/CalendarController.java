package com.kgk.web;

import com.kgk.model.admin.News;
import com.kgk.repository.admin.NewsRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;
import javax.validation.Valid;
import java.util.List;

@Controller("/api/calender")
public class CalendarController {

    private final NewsRepository newsRepository;

    public CalendarController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Get
    public List<News> listAllMeetings() {
        return newsRepository.listAllMeetings();
    }

    @Get("/{meetingId}")
    public News findNewsByMeetingId(@PathVariable("meetingId") String meetingId) {
        return newsRepository.findNewsByNewsId(meetingId);
    }

    @Post
    public News saveMeeting(@Valid @Body News meeting) {return newsRepository.saveMeeting(meeting); }

    @Put("/{meetingId}")
    public News updateMeeting(@PathVariable("meetingId") String meetingId, @Valid @Body News meeting){
        return newsRepository.updateMeeting(meetingId, meeting);
    }

    @Delete("/{meetingId}")
    public void deleteMeeting(@PathVariable("meetingId") String meetingId){ newsRepository.deleteMeeting(meetingId); }

}
