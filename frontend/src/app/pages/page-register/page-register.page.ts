import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';

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
  constructor(public formBuilder: FormBuilder, private router: Router) {
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
      occupation: [
        '',
        Validators.compose([
          Validators.required
        ])
      ],
      registrationnum: [
        '',
        Validators.compose([
        Validators.pattern('^[0-9]+$'),
        Validators.required
        ])
      ],
      mobile: [
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

  submitForm(formData: any) {
    this.submitted = true;
    if (!this.ionicForm.valid) {
      console.log('All fields are required.');
      return false;
    } else {
      console.log(formData);
    }
  }
  compareWith(o1: Occupation, o2: Occupation) {
    return o1 && o2 ? o1.name === o2.name : o1 === o2;
  }

  ngOnInit() {
  }

  regMeIn(){
    this.router.navigate(['/home']);
  }
  goBack(){
    this.router.navigate(['/login']);
  }
  notify() {
    this.isToggled = !this.isToggled;
  }


}
