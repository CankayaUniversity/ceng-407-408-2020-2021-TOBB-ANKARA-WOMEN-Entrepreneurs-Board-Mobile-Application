package com.kgk.repository.admin;

import com.kgk.model.User;
import com.kgk.model.admin.News;
import io.micronaut.core.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

public interface NewsRepository {
    static String listNewsByNewsId(String newsId) {
    //SOS
        return newsId;
    }

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
