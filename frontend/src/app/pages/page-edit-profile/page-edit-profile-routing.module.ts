import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageEditProfilePage } from './page-edit-profile.page';

const routes: Routes = [
  {
    path: '',
    component: PageEditProfilePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageEditProfilePageRoutingModule {}
