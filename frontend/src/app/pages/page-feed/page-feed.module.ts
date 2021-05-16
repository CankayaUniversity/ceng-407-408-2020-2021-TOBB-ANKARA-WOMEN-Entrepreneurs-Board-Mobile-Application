import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';

import { PageFeedPage } from './page-feed.page';
import {PageFeedCreatePageModule} from '../page-feed-create/page-feed-create.module';

const routes: Routes = [
  {
    path: '',
    component: PageFeedPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageFeedCreatePageModule,
    RouterModule.forChild(routes)
  ],
  declarations: [PageFeedPage],
  entryComponents: [PageFeedPage]
})
export class PageFeedPageModule {}
