import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: '',
    loadChildren: () => import('./pages/page-login/page-login.module').then(m => m.PageLoginPageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/page-home/page-home.module').then( m => m.PageHomePageModule)
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
    path: 'profile',
    loadChildren: () => import('./pages/page-profile/page-profile.module').then( m => m.PageProfilePageModule)
  },
  {
    path: 'message',
    loadChildren: () => import('./pages/page-message/page-message.module').then( m => m.PageMessagePageModule)
  },
  {
    path: '',
    loadChildren: () => import('./pages/page-tabs/page-tabs.module').then( m => m.PageTabsPageModule)
  },
  {
    path: 'calendar',
    loadChildren: () => import('./pages/page-calendar/page-calendar.module').then( m => m.PageCalendarPageModule)
  },  {
    path: 'page-calendar-event',
    loadChildren: () => import('./pages/page-calendar-event/page-calendar-event.module').then( m => m.PageCalendarEventPageModule)
  },





  /* For educational purposes, will be enhanced later{
    path: 'product/:id',
    loadChildren: () => import('./pages/page-register/page-register.module').then( m => m.PageRegisterPageModule)
  },*/
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
