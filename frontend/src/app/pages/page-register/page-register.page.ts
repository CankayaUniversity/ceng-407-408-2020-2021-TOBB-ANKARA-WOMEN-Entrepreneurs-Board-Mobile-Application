import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-register',
  templateUrl: './page-register.page.html',
  styleUrls: ['./page-register.page.scss'],
})
export class PageRegisterPage implements OnInit {

  public isToggled = false;
  constructor(private router: Router) { }

  ngOnInit() {
  }

  regMeIn(){
    this.router.navigate(['/home']);
  }
  goBack(){
    this.router.navigate(['/login']);
  }
  notify() {
    this.isToggled = !this.isToggled;
  }

}
