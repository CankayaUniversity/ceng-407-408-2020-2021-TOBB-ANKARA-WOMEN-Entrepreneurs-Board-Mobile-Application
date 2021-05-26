import { Component, OnInit } from '@angular/core';
import {User} from '../../providers/model/user/user.model';
import {UsersService} from '../../providers/service/users.service';
import {Router} from '@angular/router';
import {ModalController} from '@ionic/angular';
import {PageGiveMembershipPage} from '../page-give-membership/page-give-membership.page';

@Component({
  selector: 'app-page-membership-admin',
  templateUrl: './page-membership-admin.page.html',
  styleUrls: ['./page-membership-admin.page.scss'],
})
export class PageMembershipAdminPage implements OnInit {

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
      component: PageGiveMembershipPage,
      cssClass: 'calendar',
      backdropDismiss: false
    });

    await modal.present();
  }

}
