import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageFeedSinglePage } from './page-feed-single.page';

const routes: Routes = [
  {
    path: '',
    component: PageFeedSinglePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageFeedSinglePageRoutingModule {}
