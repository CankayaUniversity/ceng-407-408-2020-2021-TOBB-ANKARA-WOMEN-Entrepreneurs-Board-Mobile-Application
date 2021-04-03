import { Component, OnInit } from '@angular/core';
import { FeedService } from 'src/app/feed.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-feed',
  templateUrl: './page-feed.page.html',
  styleUrls: ['./page-feed.page.scss'],
})
export class PageFeedPage implements OnInit {
  data: any;
  page = 1;
  constructor(private feedService: FeedService, private router: Router) {}

  ngOnInit() {
    this.feedService
      .getData(
        `everything?q=tesla&from=2021-03-03&sortBy=publishedAt&pageSize=5&page=${
          this.page
        }`
      )
      .subscribe(data => {
        console.log(data);
        this.data = data;
      });
  }

  loadMoreNews(event) {
    this.page++;
    console.log(event);
    this.feedService
      .getData(
        `everything?q=tesla&from=2021-03-03&sortBy=publishedAt&pageSize=5&page=${
          this.page
        }`
      )
      .subscribe(data => {
        // console.log(data);
        // this.data = data;
        for (const article of data['articles']) {
          this.data.articles.push(article);
        }
        event.target.complete();
        console.log(this.data);
      });
  }
}
