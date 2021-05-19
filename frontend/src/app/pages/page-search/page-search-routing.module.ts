import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageSearchPage } from './page-search.page';

const routes: Routes = [
  {
    path: '',
    component: PageSearchPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageSearchPageRoutingModule {}
