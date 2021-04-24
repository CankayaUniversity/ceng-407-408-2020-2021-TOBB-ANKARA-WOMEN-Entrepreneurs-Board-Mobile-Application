import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';
import {IonContent} from '@ionic/angular';
@Component({
  selector: 'app-page-message',
  templateUrl: './page-message.page.html',
  styleUrls: ['./page-message.page.scss'],
})
export class PageMessagePage implements OnInit {

  messages = [
    {
      username: 'Katy Perry',
      createdAt: 1554090856000,
      msg: 'Finally got back that smile :)'
    },
    {
      username: 'Dua Lipa',
      createdAt: 1554090856000,
      msg: 'We created something phenomenal\n' + 'Don\'t you agree?',
    },
    {
      username: 'Taylor Swift',
      createdAt: 1554090856000,
      msg: 'You need to calm down!'
    },

  ];

  currentUser = 'Katy Perry';
  newMsg = '';
  @ViewChild(IonContent) content: IonContent;

  constructor(private router: Router) { }

  ngOnInit() {
  }
  goBack(){
    this.router.navigate(['/home']);
  }
  sendMessage(){
    this.messages.push({
        username: 'Katy Perry',
        createdAt: new Date().getTime(),
        msg: this.newMsg
      });
    this.newMsg = '';
    setTimeout(() => {
      this.content.scrollToBottom(200);
    });
  }

}
