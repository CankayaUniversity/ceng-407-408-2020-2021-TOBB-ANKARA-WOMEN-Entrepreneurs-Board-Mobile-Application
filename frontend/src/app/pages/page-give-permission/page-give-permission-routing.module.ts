import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageGivePermissionPage } from './page-give-permission.page';

const routes: Routes = [
  {
    path: '',
    component: PageGivePermissionPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageGivePermissionPageRoutingModule {}
