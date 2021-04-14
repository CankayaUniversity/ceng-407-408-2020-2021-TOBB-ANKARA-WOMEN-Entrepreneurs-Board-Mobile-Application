import { Component } from '@angular/core';
import {FeedService} from './feed.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  feed$;

  constructor(private feedService: FeedService) {}

  getFeed(){
    this.feed$ = this.feedService.getFeed();
  }
}
