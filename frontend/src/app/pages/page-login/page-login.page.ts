import {Component, Input, OnInit} from '@angular/core';
import { Router } from '@angular/router';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.page.html',
  styleUrls: ['./page-login.page.scss'],
})
export class PageLoginPage implements OnInit {
  public isToggled   = false;
  loginForm: FormGroup;
  submitted =  false;
  constructor(public formBuilder: FormBuilder, private router: Router) {
    this.loginForm = formBuilder.group({
    username: [
      '',
      Validators.compose([
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
  submitForm(formData: any) {
    this.submitted = true;
    if (!this.loginForm.valid) {
      console.log('All fields are required.');
      return false;
    } else {
      console.log(formData);
    }
  }

  logMeIn(){
    this.router.navigate(['/home']);
  }
  regMeIn(){
    this.router.navigate(['/register']);
  }

}
