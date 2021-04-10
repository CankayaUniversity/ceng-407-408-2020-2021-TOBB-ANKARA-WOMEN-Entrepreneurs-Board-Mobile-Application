package com.kgk.web;

import com.kgk.model.admin.News;
import com.kgk.repository.admin.NewsRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@Controller("/api/feed")
public class FeedController {

    private final NewsRepository newsRepository;

    public FeedController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Get
    public List<News> listAllNews() {
        return newsRepository.listAllNews();
    }

    @Get("/{newsId}")
    public News findNewsByNewsId(@PathVariable("newsId") String newsId) {
        return newsRepository.findNewsByNewsId(newsId);
    }

    @Post
    public News saveNews(@Valid @Body News news) {
        return newsRepository.saveNews(news);
    }

    @Put("/{newsId}")
    public News updateNews(@PathVariable("newsId") String newsId, @Valid @Body News news){
        return newsRepository.updateNews(newsId, news);
    }

    @Delete("/{newsId}")
    public void deleteNews(@PathVariable("newsId") String newsId){
        newsRepository.deleteNews(newsId);
    }

}
