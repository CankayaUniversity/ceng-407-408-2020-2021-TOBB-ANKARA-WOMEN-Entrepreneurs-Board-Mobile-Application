import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserApi} from '../../providers/model/user/user.api';
import {User} from '../../providers/model/user/user.model';
import {AuthService} from '../../providers/service/auth.service';
import {FormGroup} from '@angular/forms';
import {format} from 'date-fns';

@Component({
  selector: 'app-page-profile',
  templateUrl: './page-profile.page.html',
  styleUrls: ['./page-profile.page.scss'],
})
export class PageProfilePage implements OnInit {
  user: User;
  form: FormGroup;
  isOwn: boolean;

  constructor(
    private router: Router,
    private authService: AuthService,
    private route: ActivatedRoute,
    private userApi: UserApi,
  ) {
  }

  async ngOnInit() {
    this.route.paramMap.subscribe(async paramMap => {
      const userId = paramMap.get('id');
      if (userId) {
        try {
          this.user = await this.userApi.get(userId).toPromise();
        } catch (e) {
          // hata mesajı
        }
      } else {
        this.isOwn = true;
        this.user = this.authService.getUser().value;
        // this.user.birthDate = format(new Date(this.user.birthDate), 'yyyy-MM-dd');
      }
    });

  }

  // ngOnInit(){}
  logout() {
    this.router.navigate(['/login']);
  }

}
