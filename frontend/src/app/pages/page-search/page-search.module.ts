import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { PageSearchPageRoutingModule } from './page-search-routing.module';
import { PageSearchPage } from './page-search.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Ng2SearchPipeModule,
    PageSearchPageRoutingModule
  ],
  declarations: [PageSearchPage]
})
export class PageSearchPageModule {}
