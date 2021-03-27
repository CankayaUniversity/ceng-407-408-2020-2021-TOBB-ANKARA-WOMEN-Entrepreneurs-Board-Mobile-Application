import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-page-home',
  templateUrl: './page-home.page.html',
  styleUrls: ['./page-home.page.scss'],
})
export class PageHomePage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  toLogIn(){
    this.router.navigate(['/login']);
  }
}
