import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouteReuseStrategy} from '@angular/router';

import {IonicModule, IonicRouteStrategy} from '@ionic/angular';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {ProductApi} from './providers/model/product/product.api';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FeedService} from './feed.service';
import {AuthService} from './providers/service/auth.service';
import {SecurityInterceptor} from './providers/interceptor/security.interceptor';
import {LoginGuard} from './providers/guard/login.guard';
import {UserApi} from './providers/model/user/user.api';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [AppComponent],
  entryComponents: [],
  imports: [
    BrowserModule,
    IonicModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [
    FeedService,
    {provide: HTTP_INTERCEPTORS, useClass: SecurityInterceptor, multi: true},
    {provide: RouteReuseStrategy, useClass: IonicRouteStrategy},
    LoginGuard,
    ProductApi,
    UserApi,
    AuthService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
