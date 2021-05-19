import {Component, Inject, LOCALE_ID, OnInit, ViewChild} from '@angular/core';
import {CalendarComponent} from 'ionic2-calendar';
import { CalendarService } from 'src/app/providers/service/calendar.service';
import {AlertController, ModalController} from '@ionic/angular';
import {formatDate} from '@angular/common';
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
    currentDate: new Date()
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

  // Change current month/week/day
  /*next() {
    this.myCal.slideNext();
  }

  back() {
    this.myCal.slidePrev();
  }*/

  // Selected date reange and hence title changed
  /*onViewTitleChanged(title) {
    this.viewTitle = title;
  }

  // Calendar event was clicked
  async onEventSelected(event) {
    // Use Angular date pipe for conversion
    const start = formatDate(event.startTime, 'medium', this.locale);
    const end = formatDate(event.endTime, 'medium', this.locale);

    const alert = await this.alertCtrl.create({
      header: event.city,
      subHeader: event.meetingPlace,
      message: 'From: ' + start + '<br><br>To: ' + end,
      buttons: ['OK'],
    });
    alert.present();
  }*/

  toAddMeeting(){
    this.router.navigate(['/calendar-event']);
  }

  async openCalModal() {
    const modal = await this.modalCtrl.create({
      component: PageCalendarEventPage,
      cssClass: 'calendar',
      backdropDismiss: false
    });

    await modal.present();

    /*modal.onDidDismiss().then((result) => {
      if (result.data && result.data.event) {
        const event = result.data.event;
        if (event.allDay) {
          const start = event.startTime;
          event.startTime = new Date(
            Date.UTC(
              start.getUTCFullYear(),
              start.getUTCMonth(),
              start.getUTCDate()
            )
          );
          event.endTime = new Date(
            Date.UTC(
              start.getUTCFullYear(),
              start.getUTCMonth(),
              start.getUTCDate() + 1
            )
          );
        }
        this.eventSource.push(result.data.event);
        this.myCal.loadEvents();
      }
    });*/
  }
}
