import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {UserApi} from '../../providers/model/user/user.api';
import {User} from '../../providers/model/user/user.model';
import {AuthService} from '../../providers/service/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {format} from 'date-fns';

@Component({
  selector: 'app-page-profile',
  templateUrl: './page-profile.page.html',
  styleUrls: ['./page-profile.page.scss'],
})
export class PageProfilePage implements OnInit {
  user: User;
  form: FormGroup;

  constructor(private router: Router, private authService: AuthService, /*private userApi: UserApi, private formBuilder: FormBuilder*/) { }

  async ngOnInit() {
    this.user = this.authService.getUser().value;
    this.user.birthDate = format(new Date(this.user.birthDate), 'yyyy-MM-dd');

    // EDIT
    /*this.form = this.formBuilder.group({
      firstName: [this.user.firstName, Validators.required],
      lastName: [this.user.lastName]
    });*/

  }
  // ngOnInit(){}
  goBack(){
    this.router.navigate(['/feed']);
  }


  // EDIT
  /*async save(){
    const userForm = this.form.value;
    const user = await this.userApi.updateMyProfile(userForm).toPromise();
    this.authService.setUser(user);
  }*/
}
