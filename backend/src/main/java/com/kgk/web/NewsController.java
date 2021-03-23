package com.kgk.web;


import com.kgk.model.News;
import com.kgk.repository.NewsRepository;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller("/news")
public class NewsController {
    private final NewsRepository newsRepository;

    public NewsController(NewsRepository NewsRepository, NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    @Post
    public void save(@Valid @Body News news) { }

    @Delete
    public void delete(@Valid @Body News news){ newsRepository.deleteNews(news); }

    @Get
    public Collection<News> listAll() {
        return newsRepository.listAllNews();
    }

}
