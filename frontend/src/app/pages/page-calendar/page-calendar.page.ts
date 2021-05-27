import {Component, Inject, LOCALE_ID, OnInit, ViewChild} from '@angular/core';
import {CalendarComponent} from 'ionic2-calendar';
import { CalendarService } from 'src/app/providers/service/calendar.service';
import {AlertController, ModalController} from '@ionic/angular';
import {PageCalendarEventPage} from '../page-calendar-event/page-calendar-event.page';
import { Router } from '@angular/router';
import {User} from '../../providers/model/user/user.model';
import {AuthService} from 'src/app/providers/service/auth.service';

@Component({
  selector: 'app-page-calendar',
  templateUrl: './page-calendar.page.html',
  styleUrls: ['./page-calendar.page.scss'],
})
export class PageCalendarPage implements OnInit {
  calendarData: any;
  page = 1;
  eventSource = []; // events that will be displayed
  viewTitle: string; // month, week or day name

  user: User;

  calendar = {
    mode: 'month',
    currentDate: new Date(),
  };

  selectedDate: Date;
  @ViewChild(CalendarComponent) myCal: CalendarComponent;

  constructor(
    private alertCtrl: AlertController,
    @Inject(LOCALE_ID) private locale: string,
    private modalCtrl: ModalController,
    private calendarService: CalendarService,
    private router: Router,
    private authService: AuthService,
  ) { }

  ngOnInit() {
    this.calendarService.getEvents('/api/calendar')
      .subscribe(data => {
        console.log(data);
        this.calendarData = data;
      });
    this.user = this.authService.getUser().value;
  }

  next() {
    this.myCal.slideNext();
  }

  back() {
    this.myCal.slidePrev();
  }

  onViewTitleChanged(title) {
    this.viewTitle = title;
    console.log(this.viewTitle);
  }

  toAddMeeting(){
    this.router.navigate(['/calendar-event']);
  }

  redirect(meetingUrl){
    window.location.href = meetingUrl;
  }

  async openCalModal() {
    const modal = await this.modalCtrl.create({
      component: PageCalendarEventPage,
      cssClass: 'calendar',
      backdropDismiss: false
    });

    await modal.present();
  }
}
