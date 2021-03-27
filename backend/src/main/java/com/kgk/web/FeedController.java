package com.kgk.web;

import com.kgk.model.admin.News;
import com.kgk.repository.admin.NewsRepository;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller("/api/feed")
public class FeedController {
    //TODO: UPDATE METHODU YAZILACAK PUT İLE
    private final NewsRepository newsRepository;

    public FeedController(NewsRepository NewsRepository, NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    //FIXME: diğer controllerlara bakarak aşağıdaki requestleri düzenle
    @Post
    public void save(@Valid @Body News news) { }

    @Delete("/{newsId}")
    public void delete(@PathVariable("newsId") String newsId){ newsRepository.deleteNews(newsId); }

    @Get
    public Collection<News> listAll() {
        return newsRepository.listAllNews();
    }

}
