import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageTabsPageRoutingModule } from './page-tabs-routing.module';

import { PageTabsPage } from './page-tabs.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageTabsPageRoutingModule
  ],
  declarations: [PageTabsPage]
})
export class PageTabsPageModule {}
