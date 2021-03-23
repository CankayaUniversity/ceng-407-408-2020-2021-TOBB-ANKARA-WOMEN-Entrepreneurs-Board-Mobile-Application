package com.kgk.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.kgk.model.News;
import com.kgk.repository.NewsRepository;

import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class NewsRepositoryImpl implements NewsRepository {
    private final DynamoDBMapper mapper;
    private final DynamoDBMapperConfig config;

    public NewsRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }
    public Collection<News> listAllNews() {
        var scanExpression = new DynamoDBScanExpression();
        return mapper.scan(News.class, scanExpression);
    }

    public void saveNews(News news) {
        mapper.save(news);
    }
    public void deleteNews(News news) { mapper.delete(news); }
}
