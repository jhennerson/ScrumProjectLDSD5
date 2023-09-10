import { DragDropModule } from '@angular/cdk/drag-drop';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

import { TasksComponent } from './components/tasks.component';
import { OptionsComponent } from './options/options/options.component';
import { TasksRoutingModule } from './tasks-routing.module';




@NgModule({
  declarations: [
    TasksComponent,
    OptionsComponent
  ],
  imports: [
    CommonModule,
    TasksRoutingModule,
    DragDropModule,
    MatCardModule,
    MatButtonModule,
    ScrollingModule
  ]
})
export class TasksModule { }
