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
    loadChildren: () => import('./pages/page-edit-profile/page-edit-profile.module').then( m => m.PageEditProfilePageModule)
  },
  {
    path: '',
    loadChildren: () => import('./pages/page-tabs/page-tabs.module').then( m => m.PageTabsPageModule),
    canActivate: [LoginGuard],
  },


  /* For educational purposes, will be enhanced later{
    path: 'user/:id',
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
