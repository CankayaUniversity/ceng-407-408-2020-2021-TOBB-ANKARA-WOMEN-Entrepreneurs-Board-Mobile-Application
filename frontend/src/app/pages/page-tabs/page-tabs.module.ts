import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageTabsPageRoutingModule } from './page-tabs-routing.module';

import { PageTabsPage } from './page-tabs.page';

import {PageFeedPage} from '../page-feed/page-feed.page';

import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'tabs',
    component: PageTabsPage,
    children: [
      {
        path: 'feed',
        loadChildren: () => import('../page-feed/page-feed.module').then(m => m.PageFeedPageModule)
      },
    ]
  },
  {
    path: '',
    redirectTo: '/tabs/feed'
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageTabsPageRoutingModule,
    RouterModule.forChild(routes)
  ],
  declarations: [PageTabsPage]
})
export class PageTabsPageModule {}
