import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';

import { BacklogRoutingModule } from './backlog-routing.module';




@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    BacklogRoutingModule,
    MatTableModule,
    MatToolbarModule
  ]
})
export class BacklogModule { }
