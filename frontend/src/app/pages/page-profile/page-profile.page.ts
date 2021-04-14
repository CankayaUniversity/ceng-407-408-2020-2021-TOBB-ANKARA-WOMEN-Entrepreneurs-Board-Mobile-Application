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
  user: User[] = []; //
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
    catalogList: ''};

  constructor(private router: Router, private route: ActivatedRoute, /*private userApi: UserApi,*/ private http: HttpClient) { }

  async ngOnInit() {

    /*this.route.paramMap.subscribe(paramMap => {
      const id = paramMap.get('id');
    });*/
    const res = await this.http.get<User>(environment.apiUrl + '/api/{userId}').toPromise();
    this.route.paramMap.subscribe(paramMap => {
      this.users.userId = paramMap.get(res.userId);
      this.users.city = paramMap.get(res.city);
      this.users.roleId = paramMap.get(res.roleId);
      this.users.firstName = paramMap.get(res.firstName);
      this.users.lastName = paramMap.get(res.lastName);
      this.users.email = paramMap.get(res.email);
      this.users.password = paramMap.get(res.password);
      this.users.tobbRegisterId = paramMap.get(res.tobbRegisterId);
      this.users.phone = paramMap.get(res.phone);
      this.users.photo = paramMap.get(res.photo);
      this.users.birthDate = paramMap.get(String(res.birthDate));
      this.users.occupation = paramMap.get(res.occupation);
      this.users.description = paramMap.get(res.description);
      this.users.catalogList = paramMap.get(res.catalogList);
    });
    // this.users = await this.userApi.getAll().toPromise();
  }
  // ngOnInit(){}
  goBack(){
    this.router.navigate(['/login']);
  }
}
