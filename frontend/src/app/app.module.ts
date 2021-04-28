import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouteReuseStrategy} from '@angular/router';

import {IonicModule, IonicRouteStrategy} from '@ionic/angular';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {ProductApi} from './providers/model/product/product.api';
import {HttpClientModule} from '@angular/common/http';
import {FeedService} from './feed.service';

@NgModule({
  declarations: [AppComponent],
  entryComponents: [],
  imports: [
    BrowserModule,
    IonicModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [ FeedService,
    {provide: RouteReuseStrategy, useClass: IonicRouteStrategy},
    ProductApi,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
