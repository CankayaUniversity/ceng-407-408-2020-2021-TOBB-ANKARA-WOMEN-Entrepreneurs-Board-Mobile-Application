package com.kgk.repository.admin.impl;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgk.model.DeletedItem;
import com.kgk.model.admin.News;
import com.kgk.model.user.User;
import com.kgk.repository.admin.NewsRepository;
import com.kgk.repository.user.CurrentUserRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@Singleton
public class NewsRepositoryImpl implements NewsRepository {

    private static final String TABLE_NAME = "News";

    private static final String GSI_NAME = "newsByCity";

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final CurrentUserRepository currentUserRepository;

    public NewsRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config,
                              CurrentUserRepository currentUserRepository) {
        this.mapper = mapper;
        this.config = config;
        this.currentUserRepository = currentUserRepository;
    }

    @Override
    public List<News> listAllNews(/*AwsProxyRequest awsRequest*/) {
        //User currentUser = currentUserRepository.findCurrentUser(awsRequest);

        Map<String, AttributeValue> eav = new HashMap<>();
        //eav.put(":city", new AttributeValue().withS(currentUser.getCity()));
        eav.put(":city", new AttributeValue().withS("Ankara"));
        eav.put(":updatedAt", new AttributeValue().withN(String.valueOf(System.currentTimeMillis())));

        DynamoDBQueryExpression<News> queryExpression = new DynamoDBQueryExpression<News>()
                .withIndexName(GSI_NAME)
                .withKeyConditionExpression("city = :city and updatedAt < :updatedAt")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

       return mapper.query(News.class, queryExpression);
    }

    @Override
    public News findNewsByNewsId(String newsId) {
        return mapper.load(News.class, newsId, config);
    }

    @Override
    public News saveNews(News news) {
        news.setNewsId(UUID.randomUUID().toString());
        news.setPublishDate(System.currentTimeMillis());
        news.setUpdatedAt(news.getPublishDate());

        mapper.save(news);
        System.out.println("[NEWS REPO] News is saved");

        return news;
    }

    @Override
    public News updateNews(String newsId, News news) {
        News retrievedNews = findNewsByNewsId(newsId);
        retrievedNews.setCity(news.getCity());
        retrievedNews.setNewsTitle(news.getNewsTitle());
        retrievedNews.setNewsBody(news.getNewsBody());
        retrievedNews.setUpdatedAt(System.currentTimeMillis());

        mapper.save(retrievedNews);
        System.out.println("[NEWS REPO] News is updated");

        return retrievedNews;
    }

    @Override
    public void deleteNews(String newsId) {
        News news = findNewsByNewsId(newsId);
        DeletedItem deletedNews = new DeletedItem();
        deletedNews.setDeletedTime(System.currentTimeMillis());
        deletedNews.setWhichTable(TABLE_NAME);
        deletedNews.setOriginalId(news.getNewsId());

        try {
            //Creating the ObjectMapper object
            ObjectMapper om = new ObjectMapper();
            //Converting the Object to JSONString
            String json = om.writeValueAsString(news);
            deletedNews.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapper.save(deletedNews);
        System.out.println("[NEWS REPO] Deleted news is saved to DeletedItems table");

        mapper.delete(news);
        System.out.println("[NEWS REPO] News is deleted");
    }

}
