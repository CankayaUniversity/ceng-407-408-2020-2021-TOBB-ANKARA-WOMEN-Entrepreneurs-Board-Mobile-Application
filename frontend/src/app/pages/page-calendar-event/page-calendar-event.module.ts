import {AfterViewInit, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageCalendarEventPageRoutingModule } from './page-calendar-event-routing.module';

import { PageCalendarEventPage } from './page-calendar-event.page';
import {NgCalendarModule} from 'ionic2-calendar';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        PageCalendarEventPageRoutingModule,
        NgCalendarModule,
        ReactiveFormsModule,
    ],
  declarations: [PageCalendarEventPage]
})
export class PageCalendarEventPageModule{}
