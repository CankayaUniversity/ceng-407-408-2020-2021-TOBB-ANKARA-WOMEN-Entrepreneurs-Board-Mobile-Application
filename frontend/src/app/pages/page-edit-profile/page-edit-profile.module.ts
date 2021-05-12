import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageEditProfilePageRoutingModule } from './page-edit-profile-routing.module';

import { PageEditProfilePage } from './page-edit-profile.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageEditProfilePageRoutingModule
  ],
  declarations: [PageEditProfilePage]
})
export class PageEditProfilePageModule {}
