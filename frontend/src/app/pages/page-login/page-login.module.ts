import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageLoginPageRoutingModule } from './page-login-routing.module';

import { PageLoginPage } from './page-login.page';
import {InputModule} from '../../components/input/input.module';
import {TranslateModule} from "@ngx-translate/core";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    InputModule,
    PageLoginPageRoutingModule,
    ReactiveFormsModule,
    TranslateModule,
  ],
  declarations: [PageLoginPage]
})
export class PageLoginPageModule {}
