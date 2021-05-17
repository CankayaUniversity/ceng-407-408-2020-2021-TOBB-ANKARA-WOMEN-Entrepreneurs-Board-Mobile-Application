import {AfterViewInit, Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {News} from '../../providers/model/feed/feed.model';

interface City {
  name: string;
}

@Component({
  selector: 'app-page-feed-create',
  templateUrl: './page-feed-create.page.html',
  styleUrls: ['./page-feed-create.page.scss'],
})
export class PageFeedCreatePage implements OnInit {
  public isToggled   = false;
  ionicForm: FormGroup;
  submitted =  false;

  constructor(public formBuilder: FormBuilder, private router: Router, private http: HttpClient) {
    this.ionicForm = formBuilder.group({
      newsTitle: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
      newsBody: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
      city: [
        '',
        Validators.compose([
          Validators.required
        ])
      ]
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

  get errorCtr() {
    return this.ionicForm.controls;
  }

  ngOnInit(){
  }

  goBack(){
    this.router.navigate(['/tabs/feed']);
  }

  async saveNews(){
    const newsData: News = {...this.ionicForm.value};
    newsData.city = (newsData.city as any).name;
    this.submitted = true;
    if (this.ionicForm.invalid) {
      console.log('All fields are required.');
      this.router.navigate(['/tabs/feed']);
      return false;
    } else{
      console.log(newsData);
      try{
        await this.http.post(environment.apiUrl + '/api/feed', newsData).toPromise();
        this.router.navigate(['/tabs/feed']);
      } catch (e) {
        // error
      }
    }
  }
}
