import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { PageHomePageRoutingModule } from './page-home-routing.module';
import { PageHomePage } from './page-home.page';
import {TranslateModule} from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageHomePageRoutingModule,
    TranslateModule,
  ],
  declarations: [PageHomePage]
})
export class PageHomePageModule {}
