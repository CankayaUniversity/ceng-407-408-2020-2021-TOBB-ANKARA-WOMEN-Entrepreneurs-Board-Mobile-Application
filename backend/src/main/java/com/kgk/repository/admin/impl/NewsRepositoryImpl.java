package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.Catalog;
import com.kgk.model.User;
import com.kgk.model.admin.News;
import com.kgk.repository.admin.NewsRepository;
import io.micronaut.core.util.CollectionUtils;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class NewsRepositoryImpl implements NewsRepository {

    //TODO: reimplement methods according to meeting
    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public NewsRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {

        this.mapper = mapper;
        this.config = config;
    }

    public List<News> listAllNews() {

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":meeting", new AttributeValue().withBOOL(false));

        DynamoDBQueryExpression<News> queryExpression = new DynamoDBQueryExpression<News>()
                .withKeyConditionExpression("meeting = :meeting")
                .withExpressionAttributeValues(eav);

       return mapper.query(News.class, queryExpression);
    }

    public News findNewsByNewsId(String newsId) {

        return mapper.load(News.class, newsId, config);
    }

    @Override
    public List<News> listAllMeetings() {

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":meeting", new AttributeValue().withBOOL(true));

        DynamoDBQueryExpression<News> queryExpression = new DynamoDBQueryExpression<News>()
                .withKeyConditionExpression("meeting = :meeting")
                .withExpressionAttributeValues(eav);

        return mapper.query(News.class, queryExpression);
    }

    @Override
    public News saveNews(News news) {

        news.setMeeting(false);
        news.setPublishDate(System.currentTimeMillis());

        return news;
    }

    @Override
    public News saveMeeting(News meeting) {

        meeting.setMeeting(true);

        return meeting;
    }
    public News updateNews(String newsId, News news) {
        News retrievedNews = mapper.load(News.class, newsId, config);
        retrievedNews.setNewsTitle(news.getNewsTitle());
        retrievedNews.setNewsBody(news.getNewsBody());
        mapper.save(retrievedNews);
        return retrievedNews;
    }

    @Override
    public News updateMeeting(String meetingId, News meeting) {
        News retrievedMeeting = mapper.load(News.class, meetingId, config);
        retrievedMeeting.setMeetingUrl(meeting.getMeetingUrl());
        retrievedMeeting.setMeetingPlace(meeting.getMeetingPlace());
        retrievedMeeting.setStartTime(meeting.getStartTime());
        retrievedMeeting.setEndTime(meeting.getEndTime());
        mapper.save(retrievedMeeting);

        return retrievedMeeting;
    }

    @Override
    public void deleteNews(String newsId) {

        News news = mapper.load(News.class, newsId, config);
        mapper.delete(news);
    }

    @Override
    public void deleteMeeting(String meetingId) {

        News meeting = mapper.load(News.class, meetingId, config);
        mapper.delete(meeting);
    }

}
