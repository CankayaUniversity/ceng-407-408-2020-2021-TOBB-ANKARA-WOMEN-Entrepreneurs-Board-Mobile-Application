package com.kgk.repository.admin;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.kgk.model.admin.News;

import java.util.List;

public interface NewsRepository {

    List<News> listAllNews(/*AwsProxyRequest awsRequest*/);

    News findNewsByNewsId(String newsId);

    News saveNews(News news);

    News updateNews(String newsId, News news);

    void deleteNews(String newsId);

}
