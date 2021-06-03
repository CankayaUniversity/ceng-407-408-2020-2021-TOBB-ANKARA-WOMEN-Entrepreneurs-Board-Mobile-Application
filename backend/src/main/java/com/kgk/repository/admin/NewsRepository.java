package com.kgk.repository.admin;

import com.kgk.model.admin.News;

import java.util.List;

public interface NewsRepository {

    List<News> listAllNews(String userId);

    News findNewsByNewsId(String newsId);

    News saveNews(News news);

    News updateNews(String newsId, News news);

    void deleteNews(String newsId);

}
