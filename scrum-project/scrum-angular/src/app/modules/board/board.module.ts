import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BoardRoutingModule } from './board-routing.module';
import { TaskModule } from '../../modules/task/task.module';
import { MatCardModule } from '@angular/material/card';

@NgModule({
  declarations: [],
  imports: [CommonModule, BoardRoutingModule, TaskModule, MatCardModule],
})
export class BoardModule {}
