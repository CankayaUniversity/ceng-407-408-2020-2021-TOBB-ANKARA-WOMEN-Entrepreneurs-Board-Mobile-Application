import {AfterViewInit, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageCalendarEventPageRoutingModule } from './page-calendar-event-routing.module';

import { PageCalendarEventPage } from './page-calendar-event.page';
import {NgCalendarModule} from 'ionic2-calendar';
import {TranslateModule} from '@ngx-translate/core';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        PageCalendarEventPageRoutingModule,
        NgCalendarModule,
        ReactiveFormsModule,
        TranslateModule,
    ],
  declarations: [PageCalendarEventPage]
})
export class PageCalendarEventPageModule{}
