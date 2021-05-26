import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageEditProfilePageRoutingModule } from './page-edit-profile-routing.module';

import { PageEditProfilePage } from './page-edit-profile.page';
import {TranslateModule} from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageEditProfilePageRoutingModule,
    ReactiveFormsModule,
    TranslateModule,
  ],
  declarations: [PageEditProfilePage]
})
export class PageEditProfilePageModule {}
