import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/page-login/page-login.module').then(m => m.PageLoginPageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/page-home/page-home.module').then( m => m.PageHomePageModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./pages/page-register/page-register.module').then( m => m.PageRegisterPageModule)
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
