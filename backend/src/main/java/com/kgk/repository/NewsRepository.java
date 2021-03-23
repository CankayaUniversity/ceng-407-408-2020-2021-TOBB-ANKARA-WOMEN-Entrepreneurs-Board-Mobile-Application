package com.kgk.repository;

import com.kgk.model.News;

import java.util.Collection;

public interface NewsRepository {
    Collection<News> listAllNews();
    void saveNews(News news);
    void deleteNews(News news);

}
