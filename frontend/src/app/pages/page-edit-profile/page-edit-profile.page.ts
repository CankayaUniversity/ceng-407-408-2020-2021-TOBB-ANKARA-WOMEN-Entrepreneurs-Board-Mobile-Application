import { Component, OnInit } from '@angular/core';
import {User} from '../../providers/model/user/user.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../providers/service/auth.service';
import {UserApi} from '../../providers/model/user/user.api';
import {format} from 'date-fns';

interface Occupation {
  name: string;
}
interface City {
  name: string;
}

@Component({
  selector: 'app-page-edit-profile',
  templateUrl: './page-edit-profile.page.html',
  styleUrls: ['./page-edit-profile.page.scss'],
})
export class PageEditProfilePage implements OnInit {

  user: User;
  form: FormGroup;

  constructor(private router: Router, private authService: AuthService, private userApi: UserApi, private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      city: [''],
      phone: [''],
      birthDate: [''],
      occupation: [''],
      description: [''],
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

  async ngOnInit() {
    this.user = this.authService.getUser().value;

    // EDIT
    this.form = this.formBuilder.group({
      firstName: [this.user.firstName, Validators.required],
      lastName: [this.user.lastName],
      email: [this.user.email],
      city: [this.user.city],
      tobbRegisterId: [this.user.tobbRegisterId],
      phone: [this.user.tobbRegisterId],
      birthDate: [format(new Date(this.user.birthDate), 'yyyy-MM-dd')],
      occupation: [this.user.occupation],
      description: [this.user.description],
    });

  }
  // ngOnInit(){}
  goBack(){
    this.router.navigate(['/tabs/profile']);
  }

  compareWith(o1: Occupation, o2: Occupation) {
    return o1 && o2 ? o1.name === o2.name : o1 === o2;
  }
  // EDIT
  async save(){
    // const userForm = this.form.value;

    const userForm: User = {...this.form.value};
    userForm.occupation = (userForm.occupation as any).name;
    userForm.city = (userForm.city as any).name;

    const user = await this.userApi.updateMyProfile(userForm).toPromise();
    this.authService.setUser(user);
    this.router.navigate(['/tabs/feed']);
  }
}
