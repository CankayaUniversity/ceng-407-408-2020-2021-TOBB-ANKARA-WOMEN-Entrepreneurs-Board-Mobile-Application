import { Component, OnInit } from '@angular/core';
import {FeedService} from '../../feed.service';

@Component({
  selector: 'app-page-feed-single',
  templateUrl: './page-feed-single.page.html',
  styleUrls: ['./page-feed-single.page.scss'],
})
export class PageFeedSinglePage implements OnInit {
  news;
  constructor(private feedService: FeedService) { }

  ngOnInit() {
    this.news = this.feedService.currentNews;
    console.log(this.feedService.currentNews);
  }

}
