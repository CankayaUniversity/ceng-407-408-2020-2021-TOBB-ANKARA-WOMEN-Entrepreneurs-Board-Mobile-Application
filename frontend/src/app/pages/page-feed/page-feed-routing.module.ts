import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageFeedPage } from './page-feed.page';

const routes: Routes = [
  {
    path: 'feed',
    component: PageFeedPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageFeedPageRoutingModule {}
