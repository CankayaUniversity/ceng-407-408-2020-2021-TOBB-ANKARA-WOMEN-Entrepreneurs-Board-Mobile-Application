import { Component, OnInit } from '@angular/core';
import {User} from '../../providers/model/user/user.model';
import {UsersService} from '../../providers/service/users.service';
import {Router} from '@angular/router';
import {ModalController} from '@ionic/angular';
import {PageGivePermissionPage} from '../page-give-permission/page-give-permission.page';

@Component({
  selector: 'app-page-permission-admin',
  templateUrl: './page-permission-admin.page.html',
  styleUrls: ['./page-permission-admin.page.scss'],
})
export class PagePermissionAdminPage implements OnInit {

  filterTerm: string;
  user: User;
  userData: any;
  constructor(private usersService: UsersService,
              private router: Router,
              private modalCtrl: ModalController,
  ) { }

  ngOnInit() {
    this.usersService.getUsers('/api/user')
      .subscribe(data => {
        console.log(data);
        this.userData = data;
      });
  }
  goBack(){
    this.router.navigate(['/tabs/feed']);
  }
  permission(userId: string){

  }
  async giveAdmin() {
    const modal = await this.modalCtrl.create({
      component: PageGivePermissionPage,
      cssClass: 'calendar',
      backdropDismiss: false
    });

    await modal.present();
  }

}
