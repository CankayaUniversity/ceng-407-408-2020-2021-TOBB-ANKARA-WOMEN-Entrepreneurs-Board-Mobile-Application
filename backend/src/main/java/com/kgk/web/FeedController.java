package com.kgk.web;

import com.kgk.model.User;
import com.kgk.model.admin.News;
import com.kgk.repository.admin.NewsRepository;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller("/api/feed")
public class FeedController {
    //TODO: UPDATE METHODU YAZILACAK PUT İLE
    private final NewsRepository newsRepository;

    public FeedController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    //FIXME: diğer controllerlara bakarak aşağıdaki requestleri düzenle



    @Get
    public List<News> listAllNews() {
        return newsRepository.listAllNews();
    }

    @Post
    public News saveNews(@Valid @Body News news) {return newsRepository.saveNews(news); }

    @Delete("/{newsId}")
    public void deleteNews(@PathVariable("newsId") String newsId){ newsRepository.deleteNews(newsId); }


    @Put("/{newsId}")
    public News updateNews(@PathVariable("newsId") String newsId, @Valid @Body News news){
        return newsRepository.updateNews(newsId, news);
    }

    @Get("/{newsId}")
    public News findNewsByNewsId(@PathVariable("newsId") String newsId) {
        return newsRepository.findNewsByNewsId(newsId);
    }
}
