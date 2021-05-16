import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageFeedCreatePageRoutingModule } from './page-feed-create-routing.module';

import { PageFeedCreatePage } from './page-feed-create.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageFeedCreatePageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [PageFeedCreatePage]
})
export class PageFeedCreatePageModule {}
