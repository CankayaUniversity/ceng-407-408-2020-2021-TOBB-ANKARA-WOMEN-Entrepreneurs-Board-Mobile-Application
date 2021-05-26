import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageCalendarPageRoutingModule } from './page-calendar-routing.module';

import { PageCalendarPage } from './page-calendar.page';
import {NgCalendarModule} from 'ionic2-calendar';
import {TranslateModule} from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageCalendarPageRoutingModule,
    NgCalendarModule,
    TranslateModule,
  ],
  declarations: [PageCalendarPage]
})
export class PageCalendarPageModule {}
