import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {RegisterForm} from '../../providers/model/register-form.type';

interface Occupation {
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
      firstname: [
        '',
        Validators.compose([
          Validators.minLength(2),
          Validators.maxLength(50),
          Validators.pattern('[0-9a-z-A-Z-_]*'),
          Validators.required
        ])
      ],
      lastname: [
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
          Validators.pattern('[0-9a-z-A-Z@.]*'),
          Validators.required
        ])
      ],
      city: [
        '',
        Validators.compose([
          Validators.minLength(4),
          Validators.maxLength(30),
          Validators.pattern('[0-9a-z-A-Z-_]*'),
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
    const formData: RegisterForm = this.ionicForm.value;

    this.submitted = true;
    if (this.ionicForm.invalid) {
      console.log('All fields are required.');
      this.router.navigate(['/register']);
      return false;
    } else {
      console.log(formData);

      try {
        // For database action
        const res = await this.http.post<RegisterForm>(environment.apiUrl + '/api/register-form', formData).toPromise();
        this.router.navigate(['/home']);
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
