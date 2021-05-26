import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageGivePermissionPageRoutingModule } from './page-give-permission-routing.module';

import { PageGivePermissionPage } from './page-give-permission.page';
import {TranslateModule} from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageGivePermissionPageRoutingModule,
    ReactiveFormsModule,
    TranslateModule,
  ],
  declarations: [PageGivePermissionPage]
})
export class PageGivePermissionPageModule {}
