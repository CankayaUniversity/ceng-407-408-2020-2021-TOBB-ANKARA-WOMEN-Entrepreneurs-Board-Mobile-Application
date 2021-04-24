import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageMessagePageRoutingModule } from './page-message-routing.module';

import { PageMessagePage } from './page-message.page';

import { AutosizeModule } from 'ngx-autosize';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageMessagePageRoutingModule,
    AutosizeModule
  ],
  declarations: [PageMessagePage]
})
export class PageMessagePageModule {}
