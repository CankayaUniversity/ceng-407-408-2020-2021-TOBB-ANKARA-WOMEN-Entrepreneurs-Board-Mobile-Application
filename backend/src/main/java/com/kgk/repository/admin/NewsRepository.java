package com.kgk.repository.admin;

import com.kgk.model.admin.News;

import java.util.List;

public interface NewsRepository {

    List<News> listAllNews();

    List<News> listAllMeetings();

    News  findNewsByNewsId(String newsId); //find users with a specific role

    News saveNews(News news);

    News saveMeeting(News meeting);

    News updateNews(String newsId, News news);

    News updateMeeting(String meetingId, News meeting);

    void deleteNews(String newsId);

    void deleteMeeting(String meetingId);

}
