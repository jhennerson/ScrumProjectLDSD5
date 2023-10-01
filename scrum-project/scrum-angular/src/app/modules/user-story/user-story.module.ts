import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserStoryRoutingModule } from './user-story-routing.module';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    UserStoryRoutingModule,
    MatTableModule,
    MatToolbarModule,
  ],
})
export class UserStoryModule {}
