import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageHomePageRoutingModule } from './page-home-routing.module';

import { PageHomePage } from './page-home.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageHomePageRoutingModule
  ],
  declarations: [PageHomePage]
})
export class PageHomePageModule {}
