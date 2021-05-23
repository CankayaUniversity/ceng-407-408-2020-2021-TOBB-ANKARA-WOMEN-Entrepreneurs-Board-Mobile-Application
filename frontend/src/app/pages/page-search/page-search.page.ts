import { Component, OnInit } from '@angular/core';
import {User} from '../../providers/model/user/user.model';
import { UsersService } from 'src/app/providers/service/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-search',
  templateUrl: './page-search.page.html',
  styleUrls: ['./page-search.page.scss'],
})
export class PageSearchPage implements OnInit {

  filterTerm: string;
  user: User;
  userData: any;

  constructor(private usersService: UsersService,
              private router: Router,
  ) {
  }

  ngOnInit() {
    this.usersService.getUsers('/api/user')
      .subscribe(data => {
        console.log(data);
        this.userData = data;
      });
  }

  /*delete(userId: string) {

  }*/

}

