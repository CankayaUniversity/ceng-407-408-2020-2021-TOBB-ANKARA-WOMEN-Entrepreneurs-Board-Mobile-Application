import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageGivePermissionPageRoutingModule } from './page-give-permission-routing.module';

import { PageGivePermissionPage } from './page-give-permission.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageGivePermissionPageRoutingModule,
    ReactiveFormsModule,
  ],
  declarations: [PageGivePermissionPage]
})
export class PageGivePermissionPageModule {}
