import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageCalendarPage } from './page-calendar.page';

const routes: Routes = [
  {
    path: '',
    component: PageCalendarPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageCalendarPageRoutingModule {}
