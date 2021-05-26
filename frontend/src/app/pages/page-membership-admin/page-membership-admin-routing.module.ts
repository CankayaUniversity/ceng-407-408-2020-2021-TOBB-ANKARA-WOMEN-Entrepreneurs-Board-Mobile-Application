import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageMembershipAdminPage } from './page-membership-admin.page';

const routes: Routes = [
  {
    path: '',
    component: PageMembershipAdminPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageMembershipAdminPageRoutingModule {}
