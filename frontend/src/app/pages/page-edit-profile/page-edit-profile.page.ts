import { Component, OnInit } from '@angular/core';
import {User} from '../../providers/model/user/user.model';
import {FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../providers/service/auth.service';

@Component({
  selector: 'app-page-edit-profile',
  templateUrl: './page-edit-profile.page.html',
  styleUrls: ['./page-edit-profile.page.scss'],
})
export class PageEditProfilePage implements OnInit {

  user: User;
  form: FormGroup;

  constructor(private router: Router, private authService: AuthService, /*private userApi: UserApi, private formBuilder: FormBuilder*/) { }

  async ngOnInit() {
    this.user = this.authService.getUser().value;

    // EDIT
    /*this.form = this.formBuilder.group({
      firstName: [this.user.firstName, Validators.required],
      lastName: [this.user.lastName]
    });*/

  }
  // ngOnInit(){}
  goBack(){
    this.router.navigate(['/tabs/profile']);
  }


  // EDIT
  /*async save(){
    const userForm = this.form.value;
    const user = await this.userApi.updateMyProfile(userForm).toPromise();
    this.authService.setUser(user);
  }*/

  //FORM OLUCAK
}
