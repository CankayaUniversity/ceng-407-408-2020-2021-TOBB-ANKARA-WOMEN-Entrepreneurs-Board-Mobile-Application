import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PagePermissionAdminPageRoutingModule } from './page-permission-admin-routing.module';

import { PagePermissionAdminPage } from './page-permission-admin.page';
import {Ng2SearchPipeModule} from 'ng2-search-filter';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Ng2SearchPipeModule,
    PagePermissionAdminPageRoutingModule
  ],
  declarations: [PagePermissionAdminPage]
})
export class PagePermissionAdminPageModule {}
