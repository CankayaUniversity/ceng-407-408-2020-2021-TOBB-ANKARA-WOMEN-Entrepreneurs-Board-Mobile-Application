package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.kgk.model.admin.News;
import com.kgk.repository.admin.NewsRepository;

import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class NewsRepositoryImpl implements NewsRepository {

    //TODO: reimplement methods according to meeting
    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public NewsRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    public Collection<News> listAllNews() {
        //TODO: Scan yapma, query yaz, meeting'i false olanları çek
        var scanExpression = new DynamoDBScanExpression();
        return mapper.scan(News.class, scanExpression);
    }

    @Override
    public Collection<News> listAllMeetings() {
        //TODO: query yaz, meeting'i true olanları çek
        return null;
    }

    @Override
    public News saveNews(News news) {
        //TODO: meeting'i false yapıp kaydet
        return null;
    }

    @Override
    public News saveMeeting(News news) {
        //TODO: meeting'i true yapıp kaydet
        return null;
    }

    @Override
    public News updateNews(String newsId, News news) {
        return null;
    }

    @Override
    public News updateMeeting(String meetingId, News news) {
        return null;
    }

    @Override
    public void deleteNews(String newsId) {

    }

    @Override
    public void deleteMeeting(String meetingId) {

    }

}
