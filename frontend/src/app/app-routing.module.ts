import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {LoginGuard} from './providers/guard/login.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: '',
    loadChildren: () => import('./pages/page-login/page-login.module').then(m => m.PageLoginPageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/page-home/page-home.module').then( m => m.PageHomePageModule),
    canActivate: [LoginGuard],
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/page-login/page-login.module').then(m => m.PageLoginPageModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./pages/page-register/page-register.module').then( m => m.PageRegisterPageModule)
  },
  {
    path: 'profile/:id',
    loadChildren: () => import('./pages/page-profile/page-profile.module').then( m => m.PageProfilePageModule),
    canActivate: [LoginGuard],
  },
  {
    path: 'profile',
    loadChildren: () => import('./pages/page-profile/page-profile.module').then( m => m.PageProfilePageModule),
    canActivate: [LoginGuard],
  },
  {
    path: 'message',
    loadChildren: () => import('./pages/page-message/page-message.module').then( m => m.PageMessagePageModule),
    canActivate: [LoginGuard],
  },
  {
    path: 'edit-profile',
    loadChildren: () => import('./pages/page-edit-profile/page-edit-profile.module').then( m => m.PageEditProfilePageModule),
    canActivate: [LoginGuard],
  },
  {
    path: 'give-permission',
    loadChildren: () => import('./pages/page-give-permission/page-give-permission.module').then( m => m.PageGivePermissionPageModule)
  },
  {
    path: 'permission-admin',
    loadChildren: () => import('./pages/page-permission-admin/page-permission-admin.module').then( m => m.PagePermissionAdminPageModule)
  },
  {
    path: 'feed-single',
    loadChildren: () => import('./pages/page-feed-single/page-feed-single.module').then( m => m.PageFeedSinglePageModule)
  },
 {
    path: '',
    loadChildren: () => import('./pages/page-tabs/page-tabs.module').then( m => m.PageTabsPageModule),
    canActivate: [LoginGuard],
  },
  {
    path: 'calendar',
    loadChildren: () => import('./pages/page-calendar/page-calendar.module').then( m => m.PageCalendarPageModule)
  },
  {
    path: 'calendar-event',
    loadChildren: () => import('./pages/page-calendar-event/page-calendar-event.module').then( m => m.PageCalendarEventPageModule)
  },
  {
    path: 'feed-create',
    loadChildren: () => import('./pages/page-feed-create/page-feed-create.module').then( m => m.PageFeedCreatePageModule)
  },
  {
    path: 'search',
    loadChildren: () => import('./pages/page-search/page-search.module').then( m => m.PageSearchPageModule)
  },






];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
