import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';
import {IonContent} from '@ionic/angular';
import {User} from '../../providers/model/user/user.model';
import {AuthService} from '../../providers/service/auth.service';
@Component({
  selector: 'app-page-message',
  templateUrl: './page-message.page.html',
  styleUrls: ['./page-message.page.scss'],
})
export class PageMessagePage implements OnInit {

  messages = [
    {
      username: 'Group Admin',
      createdAt: 1554090856000,
      msg: 'Welcome To W-COOP Mobile Application!'
    },
    {
      username: 'Group Admin',
      createdAt: 1554090856000,
      msg: 'Here, you can find every new announcement about W-COOP and TOBB AKGK!',
    },
  ];

  currentUser;
  newMsg = '';
  user: User;
  @ViewChild(IonContent) content: IonContent;
  constructor(private router: Router, private authService: AuthService) {
  }

  async ngOnInit() {
    this.user = this.authService.getUser().value;
    this.currentUser =  this.user.firstName + ' ' + this.user.lastName;
  }
  goBack(){
    this.router.navigate(['/tabs/feed']);
  }
  sendMessage(){
    this.messages.push({
        username: this.user.firstName + ' ' + this.user.lastName,
        createdAt: new Date().getTime(),
        msg: this.newMsg
      });
    this.newMsg = '';
    setTimeout(() => {
      this.content.scrollToBottom(200);
    });
  }

}
