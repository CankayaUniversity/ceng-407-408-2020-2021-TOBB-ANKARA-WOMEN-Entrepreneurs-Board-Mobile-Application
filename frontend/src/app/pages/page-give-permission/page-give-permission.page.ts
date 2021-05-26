import { Component, OnInit } from '@angular/core';
import {User} from '../../providers/model/user/user.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../providers/service/auth.service';
import {ModalController} from '@ionic/angular';
import {UserApi} from '../../providers/model/user/user.api';

interface RoleId {
  name: string;
}

@Component({
  selector: 'app-page-give-permission',
  templateUrl: './page-give-permission.page.html',
  styleUrls: ['./page-give-permission.page.scss'],
})
export class PageGivePermissionPage implements OnInit {
  user: User;
  form: FormGroup;
  viewTitle: string;
  constructor(private router: Router, public formBuilder: FormBuilder, private authService: AuthService, private modalCtrl: ModalController, private userApi: UserApi) {
    this.form = this.formBuilder.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      city: [''],
      phone: [''],
      birthDate: [''],
      occupation: [''],
      description: [''],
      roleId: [''],
    });
  }
  roles: RoleId[] = [
    /*{
      name: 'CV_ADMIN',
    },
    {
      name: 'FULL_ADMIN',
    },*/
    {
      name: 'GROUP_ADMIN',
    },
    /*{
      name: 'MEETING_ADMIN',
    },*/
    {
      name: 'MEMBER',
    },
    {
      name: 'MEMBERSHIP_ADMIN',
    },
    {
      name: 'PERMISSION_ADMIN',
    },
  ];
  async ngOnInit() {
    this.user = this.authService.getUser().value;
    this.form = this.formBuilder.group({
      firstName: [this.user.firstName, Validators.required],
      lastName: [this.user.lastName],
      email: [this.user.email],
      city: [this.user.city],
      tobbRegisterId: [this.user.tobbRegisterId],
      phone: [this.user.tobbRegisterId],
      birthDate: [this.user.birthDate ? new Date(this.user.birthDate) : null],
      occupation: [this.user.occupation],
      description: [this.user.description],
      roleId: [this.user.roleId],
    });
  }
  compareWith(o1: RoleId, o2: RoleId) {
    return o1 && o2 ? o1.name === o2.name : o1 === o2;
  }
  close() {
    this.modalCtrl.dismiss();
  }
  async save(){
    // const userForm = this.form.value;

    const userForm: User = {...this.form.value};
    userForm.occupation = (userForm.occupation as any).name;
    userForm.city = (userForm.city as any).name;
    userForm.roleId = (userForm.roleId as any).name;

    const user = await this.userApi.updateMyProfile(userForm).toPromise();
    this.authService.setUser(user);
    this.router.navigate(['/tabs/feed']);
  }
}
