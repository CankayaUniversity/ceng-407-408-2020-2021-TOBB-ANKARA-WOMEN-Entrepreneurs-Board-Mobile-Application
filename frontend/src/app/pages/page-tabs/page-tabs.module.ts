import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { PageTabsPageRoutingModule } from './page-tabs-routing.module';
import { PageTabsPage } from './page-tabs.page';
import { Routes, RouterModule } from '@angular/router';
import {PageFeedPageModule} from '../page-feed/page-feed.module';


const routes: Routes = [
  {
    path: 'tabs',
    component: PageTabsPage,
    children: [
      {
        path: 'feed',
        loadChildren: () => import('../page-feed/page-feed.module').then(m => m.PageFeedPageModule)
      },
      {
        path: 'calendar',
        loadChildren: () => import('../page-calendar/page-calendar.module').then(m => m.PageCalendarPageModule)
      },
      {
        path: 'profile',
        loadChildren: () => import('../page-profile/page-profile.module').then(m => m.PageProfilePageModule)
      },
      {
        path: 'feed-create',
        loadChildren: () => import('../page-feed-create/page-feed-create.module').then(m => m.PageFeedCreatePageModule)
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
    PageFeedPageModule,
    RouterModule.forChild(routes)
  ],
  declarations: [PageTabsPage]
})
export class PageTabsPageModule {}
