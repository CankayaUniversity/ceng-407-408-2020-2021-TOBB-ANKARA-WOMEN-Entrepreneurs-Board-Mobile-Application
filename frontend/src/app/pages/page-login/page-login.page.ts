import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.page.html',
  styleUrls: ['./page-login.page.scss'],
})
export class PageLoginPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  logMeIn(){
    this.router.navigate(['/home']);
  }
  regMeIn(){
    this.router.navigate(['/register']);
  }

}
