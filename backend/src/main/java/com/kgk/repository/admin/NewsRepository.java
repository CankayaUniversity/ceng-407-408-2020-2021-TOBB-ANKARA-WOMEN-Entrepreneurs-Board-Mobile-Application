package com.kgk.repository.admin;

import com.kgk.model.admin.News;
import io.micronaut.core.util.CollectionUtils;

import java.util.Collection;

public interface NewsRepository {
    Collection<News> listAllNews();

    Collection<News> listAllMeetings();

    News saveNews(News news);

    News saveMeeting(News news);

    News updateNews(String newsId, News news);

    News updateMeeting(String meetingId, News news);

    void deleteNews(String newsId);

    void deleteMeeting(String meetingId);

}
