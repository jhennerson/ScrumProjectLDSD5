import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, map, of } from 'rxjs';

import { Task } from '../../models/task/task';
import { TaskService } from '../../services/task/task.service';
import { Sprint } from '../../models/sprint/sprint';

@Component({
  selector: 'app-backlog',
  templateUrl: './backlog.component.html',
  styleUrls: ['./backlog.component.scss']
})
export class BacklogComponent {
  tasks: Observable<Task[]>;
  displayedColumns = ['actions', 'title', 'assignedTo', 'endDate', 'status'];

  constructor(
    private taskService: TaskService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {
    this.tasks = this.taskService.list();
  }

  sprints: Sprint[] = [
    {
      id: '1',
      title: 'Sprint atual',
      assignmentDate: new Date(),
      endDate: new Date(),
      description: 'Sprint atual',
      status: 'Em execução',
    },
  ];

  onExtend() {
    console.log('on Extend!');
  }
}
