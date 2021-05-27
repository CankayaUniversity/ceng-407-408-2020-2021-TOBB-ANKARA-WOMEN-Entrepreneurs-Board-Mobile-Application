import { Component, OnInit } from '@angular/core';
import {FeedService} from '../../feed.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-page-feed-single',
  templateUrl: './page-feed-single.page.html',
  styleUrls: ['./page-feed-single.page.scss'],
})
export class PageFeedSinglePage implements OnInit {
  news;
  constructor(private feedService: FeedService, private router: Router) { }

  ngOnInit() {
    this.news = this.feedService.currentNews;
    console.log(this.feedService.currentNews);
  }

  goBack(){
    this.router.navigate(['/tabs/feed']);
  }
}

