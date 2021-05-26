import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageProfilePageRoutingModule } from './page-profile-routing.module';

import { PageProfilePage } from './page-profile.page';
import {TranslateModule} from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PageProfilePageRoutingModule,
    TranslateModule,
  ],
  declarations: [PageProfilePage]
})
export class PageProfilePageModule {}
