import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {LoginForm} from '../../providers/model/login-form.type';

import {User} from '../../providers/model/user/user.model';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.page.html',
  styleUrls: ['./page-login.page.scss'],
})
export class PageLoginPage implements OnInit {
  public isToggled   = false;
  loginForm: FormGroup;
  submitted =  false;
  /*user: User[] = []; //
  users = {userId: '',
    city: '' ,
    roleId: '',
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    tobbRegisterId: '',
    phone: '',
    photo: '',
    birthDate: '',  //
    occupation: '',
    description: '',
    catalogList: ''};*/
  constructor(public formBuilder: FormBuilder, private router: Router, private http: HttpClient, private route: ActivatedRoute) {

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
      /*this.route.paramMap.subscribe(paramMap => {
        this.users.userId = paramMap.get('userId');
        this.users.city = paramMap.get('city');
        this.users.roleId = paramMap.get('roleId');
        this.users.firstName = paramMap.get('firstName');
        this.users.lastName = paramMap.get('lastName');
        this.users.email = paramMap.get('email');
        this.users.password = paramMap.get('password');
        this.users.tobbRegisterId = paramMap.get('tobbRegisterId');
        this.users.phone = paramMap.get('phone');
        this.users.photo = paramMap.get('photo');
        this.users.birthDate = paramMap.get('birthDate');
        this.users.occupation = paramMap.get(res.occupation);
        this.users.description = paramMap.get('description');
        this.users.catalogList = paramMap.get('catalogList');
      });*/
      console.log(formData);
      // console.log('Users', this.users);
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
  /*click(user: User){
    this.router.navigate(['/profile', user.email]);
  }*/

}
