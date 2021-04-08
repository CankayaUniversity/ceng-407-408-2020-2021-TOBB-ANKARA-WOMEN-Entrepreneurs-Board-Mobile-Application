import {Component, Input, OnInit} from '@angular/core';
import { Router } from '@angular/router';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {LoginForm} from '../../providers/model/login-form.type';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.page.html',
  styleUrls: ['./page-login.page.scss'],
})
export class PageLoginPage implements OnInit {
  public isToggled   = false;
  loginForm: FormGroup;
  submitted =  false;
  constructor(public formBuilder: FormBuilder, private router: Router, private http: HttpClient) {
    this.loginForm = formBuilder.group({
    email: [
      '',
      Validators.compose([
        Validators.pattern('[A-Za-z0-9._%+-]{2,}@[a-zA-Z-_.]{2,}[.]{1}[a-zA-Z]{2,}'),
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
      try {
        // For database action
        const res = await this.http.post<LoginForm>(environment.apiUrl + '/api/login-form', formData).toPromise();
        this.router.navigate(['/home']);
      } catch (e) {
        // error toast
      }
    }
  }

  logMeIn(){
    this.router.navigate(['/home']);
  }
  regMeIn(){
    this.router.navigate(['/register']);
  }

}
