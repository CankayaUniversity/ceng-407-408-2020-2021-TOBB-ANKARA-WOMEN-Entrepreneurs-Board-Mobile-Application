import { Component } from '@angular/core';
import {FeedService} from './feed.service';
import {environment} from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  feed;
  constructor(private feedService: FeedService) {}

  getFeed(apiUrl: string){
    this.feed = this.feedService.getFeed(environment.apiUrl);
  }
}
