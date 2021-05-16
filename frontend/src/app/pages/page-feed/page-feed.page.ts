import {Component, Inject, LOCALE_ID, OnInit} from '@angular/core';
import { FeedService } from 'src/app/feed.service';
import { Router } from '@angular/router';
import {environment} from '../../../environments/environment';
import {AuthService} from '../../providers/service/auth.service';
import {User} from '../../providers/model/user/user.model';
import {PageFeedCreatePage} from '../page-feed-create/page-feed-create.page';
import {AlertController, ModalController} from '@ionic/angular';
import {PageFeedCreatePageModule} from '../page-feed-create/page-feed-create.module';

@Component({
  selector: 'app-page-feed',
  templateUrl: './page-feed.page.html',
  styleUrls: ['./page-feed.page.scss'],
})

export class PageFeedPage implements OnInit {
  data: any;
  page = 1;
  user: User;

  constructor(
    private alertCtrl: AlertController,
    private feedService: FeedService,
    private modalCtrl: ModalController,
    private router: Router,
    private authService: AuthService,
    @Inject(LOCALE_ID) private locale: string,
  ) {}

  ngOnInit() {
    this.feedService.getFeed('/api/feed')
      .subscribe(data => {
        console.log(data);
        this.data = data;
      });
    this.user = this.authService.getUser().value;
/*    this.authService.getUser().subscribe(user => {
      this.user = user;
    });*/
  }

  onGoToNewsSingle(news){
    this.feedService.currentNews = news;
    this.router.navigate(['/page-feed-single']);
  }

  async addNews(){
    const modal = await this.modalCtrl.create({
      component: PageFeedCreatePage,
      cssClass: 'feed-create',
      backdropDismiss: false
    });

    await modal.present;
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
  message(){
    this.router.navigate(['/message']);
  }
}
