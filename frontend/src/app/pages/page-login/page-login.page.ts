import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {LoginForm} from '../../providers/model/login-form.type';

import {User} from '../../providers/model/user/user.model';
import {AuthService} from '../../providers/service/auth.service';
import {Credential} from '../../providers/model/user/credential.model';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.page.html',
  styleUrls: ['./page-login.page.scss'],
})
export class PageLoginPage implements OnInit {
  public isToggled   = false;
  loginForm: FormGroup;
  submitted =  false;

  constructor(
    public formBuilder: FormBuilder,
    private router: Router,
    private http: HttpClient,
    private route: ActivatedRoute,
    public translate: TranslateService,
    private authService: AuthService,
    ) {

    this.loginForm = formBuilder.group({
    username: [
      '',
      Validators.compose([
        Validators.email,
        Validators.required
      ])
    ],
    password: [
      '',
      Validators.compose([
        Validators.required
      ])
    ],
  }); }

  ngOnInit() {
  }
  get errorCtr() {
    return this.loginForm.controls;
  }
  async submitForm() {
    const formData: LoginForm = this.loginForm.value;
    this.submitted = true;
    if (this.loginForm.invalid) {
      console.log('All fields are required.');
      this.router.navigate(['/login']);
      return false;
    } else {

      console.log(formData);
      // console.log('Users', this.users);
      try {
        // For database action
        const credential = await this.http.post<Credential>(environment.apiUrl + '/login', formData).toPromise();
        this.authService.setCredential(credential);
        const user = await this.http.get<User>(environment.apiUrl + '/api/user/profile').toPromise();
        this.authService.setUser(user);
        this.router.navigate(['/tabs/feed']);
      } catch (e) {
        // error toast
      }
    }
  }

  logMeIn(){
    this.router.navigate(['/tabs/feed']);
  }
  regMeIn(){
    this.router.navigate(['/register']);
  }
  changeLang(lang: string) {
    this.translate.setDefaultLang(lang);
  }


}
