import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PagePermissionAdminPage } from './page-permission-admin.page';

const routes: Routes = [
  {
    path: '',
    component: PagePermissionAdminPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagePermissionAdminPageRoutingModule {}
