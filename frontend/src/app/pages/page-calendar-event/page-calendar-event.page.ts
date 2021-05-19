import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ModalController} from '@ionic/angular';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {CalendarEvent} from '../../providers/model/calendar-event/calendar-event.model';
import {environment} from '../../../environments/environment';

interface City{
  name: string;
}
@Component({
  selector: 'app-page-calendar-event',
  templateUrl: './page-calendar-event.page.html',
  styleUrls: ['./page-calendar-event.page.scss'],
})
export class PageCalendarEventPage implements AfterViewInit {
  calendar = {
    mode: 'month',
    currentDate: new Date()
  };

  event = {
    city: '',
    meetingPlace: '',
    meetingUrl: '',
    endTime: '',
    startTime: '',
    meetingDate: '',
  };

  viewTitle: string;
  ionicForm: FormGroup;
  submitted;

  modalReady = false;

  constructor(public formBuilder: FormBuilder, private modalCtrl: ModalController, private router: Router, private http: HttpClient) {
    this.ionicForm = formBuilder.group({
      city: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
      meetingPlace: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
      meetingUrl: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
      startTime: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
      endTime: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
    });
  }

  cities: City[] = [
    {
      name: 'Adana',
    },
    {
      name: 'Adıyaman',
    },
    {
      name: 'Afyonkarahisar',
    },
    {
      name: 'Ağrı',
    },
    {
      name: 'Aksaray',
    },
    {
      name: 'Amasya',
    },
    {
      name: 'Ankara',
    },
    {
      name: 'Antalya',
    },
    {
      name: 'Ardahan',
    },
    {
      name: 'Artvin',
    },
    {
      name: 'Aydın',
    },
    {
      name: 'Balıkesir',
    },
    {
      name: 'Bartın',
    },
    {
      name: 'Batman',
    },
    {
      name: 'Bayburt',
    },
    {
      name: 'Bilecik',
    },
    {
      name: 'Bingöl',
    },
    {
      name: 'Bitlis',
    },
    {
      name: 'Bolu',
    },
    {
      name: 'Burdur',
    },
    {
      name: 'Bursa',
    },
    {
      name: 'Çanakkale',
    },
    {
      name: 'Çankırı',
    },
    {
      name: 'Çorum',
    },
    {
      name: 'Denizli',
    },
    {
      name: 'Diyarbakır',
    },
    {
      name: 'Düzce',
    },
    {
      name: 'Edirne',
    },
    {
      name: 'Elazığ',
    },
    {
      name: 'Erzincan',
    },
    {
      name: 'Erzurum',
    },
    {
      name: 'Eskişehir',
    },
    {
      name: 'Gaziantep',
    },
    {
      name: 'Giresun',
    },
    {
      name: 'Gümüşhane',
    },
    {
      name: 'Hakkari',
    },
    {
      name: 'Hatay',
    },
    {
      name: 'Iğdır',
    },
    {
      name: 'Isparta',
    },
    {
      name: 'İstanbul',
    },
    {
      name: 'İzmir',
    },
    {
      name: 'Kahramanmaraş',
    },
    {
      name: 'Karabük',
    },
    {
      name: 'Karaman',
    },
    {
      name: 'Kars',
    },
    {
      name: 'Kastamonu',
    },
    {
      name: 'Kayseri',
    },
    {
      name: 'Kırıkkale',
    },
    {
      name: 'Kırklareli',
    },
    {
      name: 'Kırşehir',
    },
    {
      name: 'Kilis',
    },
    {
      name: 'Kocaeli',
    },
    {
      name: 'Konya',
    },
    {
      name: 'Kütahya',
    },
    {
      name: 'Malatya',
    },
    {
      name: 'Manisa',
    },
    {
      name: 'Mardin',
    },
    {
      name: 'Mersin',
    },
    {
      name: 'Muğla',
    },
    {
      name: 'Muş',
    },
    {
      name: 'Nevşehir',
    },
    {
      name: 'Niğde',
    },
    {
      name: 'Ordu',
    },
    {
      name: 'Osmaniye',
    },
    {
      name: 'Rize',
    },
    {
      name: 'Sakarya',
    },
    {
      name: 'Samsun',
    },
    {
      name: 'Siirt',
    },
    {
      name: 'Sinop',
    },
    {
      name: 'Sivas',
    },
    {
      name: 'Şanlıurfa',
    },
    {
      name: 'Şırnak',
    },
    {
      name: 'Tekirdağ',
    },
    {
      name: 'Tokat',
    },
    {
      name: 'Trabzon',
    },
    {
      name: 'Tunceli',
    },
    {
      name: 'Uşak',
    },
    {
      name: 'Van',
    },
    {
      name: 'Yalova',
    },
    {
      name: 'Yozgat',
    },
    {
      name: 'Zonguldak',
    }
  ];

  ngAfterViewInit() {
    setTimeout(() => {
      this.modalReady = true;
    }, 0);
  }

  async saveEvent() {
    const eventData: CalendarEvent = {...this.ionicForm.value};
    eventData.city = (eventData.city as any).name;

    this.submitted = true;
    if (this.ionicForm.invalid) {
      console.log('All fields are required.');
      this.router.navigate(['/tabs/calendar']);
      return false;
    }else{
      console.log(eventData);
      try{
        await this.http.post(environment.apiUrl + '/api/calendar', eventData).toPromise();
        this.router.navigate(['/tabs/calendar']);
      } catch (e){
        // error
      }
    }
    this.modalCtrl.dismiss({event: this.event});
  }

  get errorCtr() {
    return this.ionicForm.controls;
  }

  close() {
    this.modalCtrl.dismiss();
  }

}
