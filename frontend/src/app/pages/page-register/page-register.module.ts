import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { IonicModule } from '@ionic/angular';

import { PageRegisterPageRoutingModule } from './page-register-routing.module';

import { PageRegisterPage } from './page-register.page';
import {TranslateModule} from '@ngx-translate/core';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageRegisterPageRoutingModule,
    ReactiveFormsModule,
    TranslateModule,
  ],
  declarations: [PageRegisterPage]
})
export class PageRegisterPageModule {}
