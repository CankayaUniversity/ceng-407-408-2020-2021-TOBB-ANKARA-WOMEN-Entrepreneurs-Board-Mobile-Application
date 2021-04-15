import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageFeedSinglePageRoutingModule } from './page-feed-single-routing.module';

import { PageFeedSinglePage } from './page-feed-single.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageFeedSinglePageRoutingModule
  ],
  declarations: [PageFeedSinglePage]
})
export class PageFeedSinglePageModule {}
