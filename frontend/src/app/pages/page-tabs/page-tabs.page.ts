import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-page-tabs',
  templateUrl: './page-tabs.page.html',
  styleUrls: ['./page-tabs.page.scss'],
})
export class PageTabsPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }
  /*profile(){
    this.router.navigate(['/tabs/profile']);
  }*/

}
