import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {RegisterForm} from '../../providers/model/register-form.type';

interface Occupation {
  name: string;
}
interface City {
  name: string;
}

@Component({
  selector: 'app-page-register',
  templateUrl: './page-register.page.html',
  styleUrls: ['./page-register.page.scss'],
})
export class PageRegisterPage implements OnInit {
  public isToggled   = false;
  ionicForm: FormGroup;
  submitted =  false;
  constructor(public formBuilder: FormBuilder, private router: Router, private http: HttpClient) {
    this.ionicForm = formBuilder.group({
      firstName: [
        '',
        Validators.compose([
          Validators.minLength(2),
          Validators.maxLength(50),
          Validators.pattern('[0-9a-z-A-Z-_]*'),
          Validators.required
        ])
      ],
      lastName: [
        '',
        Validators.compose([
          Validators.minLength(2),
          Validators.maxLength(50),
          Validators.pattern('[0-9a-z-A-Z-_]*'),
          Validators.required
        ])
      ],
      email: [
        '',
        Validators.compose([
          Validators.minLength(4),
          Validators.pattern('[A-Za-z0-9._%+-]{2,}@[a-zA-Z-_.]{2,}[.]{1}[a-zA-Z]{2,}'),
          Validators.required
        ])
      ],
      city: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
      password: [
        '',
        Validators.compose([
          Validators.minLength(6),
          Validators.pattern('[0-9a-z-A-Z@.#*$!?&+-/]*'),
          Validators.required
        ])
      ],
      occupation: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
      tobbRegisterId: [
        '',
        Validators.compose([
        Validators.pattern('^[0-9]+$'),
        Validators.required
        ])
      ],
      phone: [
        '',
        Validators.compose([
          Validators.pattern('^[0-9]+$'),
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

  occupations: Occupation[] = [
    {
      name: 'Adalet ve Güvenlik',
    },
    {
      name: 'Ağaç İşleri, Kağıt ve Kağıt Ürünleri',
    },
    {
      name: 'Bilişim Teknolojileri',
    },
    {
      name: 'Cam, Çimento ve Toprak',
    },
    {
      name: 'Çevre',
    },
    {
      name: 'Eğitim',
    },
    {
      name: 'Elektrik ve Elektronik',
    },
    {
      name: 'Enerji',
    },
    {
      name: 'Finans',
    },
    {
      name: 'Gıda',
    },
    {
      name: 'İnşaat',
    },
    {
      name: 'İş ve Yönetim',
    },
    {
      name: 'Kimya, Petrol, Lastik ve Plastik',
    },
    {
      name: 'Kültür, Sanat ve Tasarım',
    },
    {
      name: 'Maden',
    },
    {
      name: 'Medya, İletişim ve Yayıncılık',
    },
    {
      name: 'Metal',
    },
    {
      name: 'Otomotiv',
    },
    {
      name: 'Sağlık ve Sosyal Hizmetler',
    },
    {
      name: 'Spor ve Rekreasyon',
    },
    {
      name: 'Tarım, Avcılık ve Balıkçılık',
    },
    {
      name: 'Tekstil, Hazır Giyim, Deri',
    },
    {
      name: 'Ticaret (Satış ve Pazarlama)',
    },
    {
      name: 'Toplumsal ve Kişisel Hizmetler',
    },
    {
      name: 'Turizm, Konaklama, Yiyecek-İçecek Hizmetleri',
    },
    {
      name: 'Ulaştırma, Lojistik ve Haberleşme',
    }
  ];

  get errorCtr() {
    return this.ionicForm.controls;
  }

  async submitForm() {
    const formData: RegisterForm = {...this.ionicForm.value};
    formData.occupation = (formData.occupation as any).name;
    formData.city = (formData.city as any).name;

    this.submitted = true;
    if (this.ionicForm.invalid && this.isToggled) {
      console.log('All fields are required.');
      this.router.navigate(['/register']);
      return false;
    } else {
      console.log(formData);
      try {
        // For database action
        const res = await this.http.post<RegisterForm>(environment.apiUrl + '/api/register-form', formData).toPromise();
        this.router.navigate(['/login']);
      } catch (e) {
        // error toast
      }

    }
  }
  compareWith(o1: Occupation, o2: Occupation) {
    return o1 && o2 ? o1.name === o2.name : o1 === o2;
  }

  ngOnInit() {
  }

  /*This method might come in handy for ion-button in the future
  regMeIn(){
    this.router.navigate(['/home']);
  }*/
  goBack(){
    this.router.navigate(['/login']);
  }
  notify() {
    this.isToggled = !this.isToggled;
  }


}
