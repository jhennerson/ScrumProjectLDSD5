import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WasteBinRoutingModule } from './waste-bin-routing.module';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    WasteBinRoutingModule,
    MatTableModule,
    MatToolbarModule,
  ],
})
export class WasteBinModule {}
