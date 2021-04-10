import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {UserApi} from '../../providers/model/user/user.api';
import {User} from '../../providers/model/user/user.model';
@Component({
  selector: 'app-page-profile',
  templateUrl: './page-profile.page.html',
  styleUrls: ['./page-profile.page.scss'],
})
export class PageProfilePage implements OnInit {

  // users: User[];

  constructor(private router: Router/*, private route: ActivatedRoute, private userApi: UserApi, private http: HttpClient*/) { }

  /*async ngOnInit() {

    this.route.paramMap.subscribe(paramMap => {
      const id = paramMap.get('id');
    });
    const res = await this.http.get<User>(environment.apiUrl + '/api/{userId}').toPromise();
    this.users = await this.userApi.getAll().toPromise();
  }*/
  ngOnInit(){}
  goBack(){
    this.router.navigate(['/login']);
  }
}
