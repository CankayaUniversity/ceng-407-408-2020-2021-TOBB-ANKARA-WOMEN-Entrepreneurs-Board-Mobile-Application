import { Component, OnInit } from '@angular/core';
import { FeedService } from 'src/app/feed.service';
import { Router } from '@angular/router';
import {environment} from '../../../environments/environment';

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
    this.feedService.getFeed('/api/feed')
      .subscribe(data => {
        console.log(data);
        this.data = data;
      });
  }

  onGoToNewsSingle(news){
    this.feedService.currentNews = news;
    this.router.navigate(['/feed-single']);
  }

  loadMoreNews(event) {
    this.page++;
    console.log(event);
    this.feedService.getFeed(environment.apiUrl)
      .subscribe(data => {
        // console.log(data);
        // this.data = data;
        for (const news of 'feed') {
          this.data.feed.push(news);
        }
        event.target.complete();
        console.log(this.data);
      });
  }
}
