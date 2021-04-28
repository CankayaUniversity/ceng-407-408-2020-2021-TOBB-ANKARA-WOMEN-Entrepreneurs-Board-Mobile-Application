import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageTabsPage } from './page-tabs.page';

const routes: Routes = [
  {
    path: 'tabs',
    component: PageTabsPage,
    children: [
      {
        path: '',
        loadChildren: () => import('../page-feed/page-feed.module').then(m => m.PageFeedPageModule)
      },
      {
        path: '',
        redirectTo: '/tabs/feed',
        pathMatch: 'full'
      },
    ],
  },
  {
    path: '',
    redirectTo: '/tabs/feed',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageTabsPageRoutingModule {}
