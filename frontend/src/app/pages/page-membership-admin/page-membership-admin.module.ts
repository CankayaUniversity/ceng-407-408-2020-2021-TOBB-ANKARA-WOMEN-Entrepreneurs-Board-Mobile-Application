import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PageMembershipAdminPageRoutingModule } from './page-membership-admin-routing.module';

import { PageMembershipAdminPage } from './page-membership-admin.page';
import {Ng2SearchPipeModule} from 'ng2-search-filter';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Ng2SearchPipeModule,
    PageMembershipAdminPageRoutingModule
  ],
  declarations: [PageMembershipAdminPage]
})
export class PageMembershipAdminPageModule {}
