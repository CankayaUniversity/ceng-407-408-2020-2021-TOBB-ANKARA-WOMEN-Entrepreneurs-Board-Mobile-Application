import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageFeedCreatePage } from './page-feed-create.page';

const routes: Routes = [
  {
    path: '',
    component: PageFeedCreatePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageFeedCreatePageRoutingModule {}
