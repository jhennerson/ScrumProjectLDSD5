import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';

import { Sprint } from '../../models/sprint/sprint';
import { Task } from '../../models/task/task';
import { TaskService } from '../../services/task/task.service';
import { ExtendedTaskModalComponent } from 'src/app/shared/components/extended-task-modal/extended-task-modal.component';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

@Component({
  selector: 'app-backlog',
  templateUrl: './backlog.component.html',
  styleUrls: ['./backlog.component.scss'],
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

  onAdd() {
    this.dialog.open(TaskFormModalComponent, {
      width: `80%`,
      height: `80%`,
    });
  }

  onExtend() {
    this.dialog.open(ExtendedTaskModalComponent, {
      width: `80%`,
      height: `80%`,
    });
  }
}
