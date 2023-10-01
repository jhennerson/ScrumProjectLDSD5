import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

import { Sprint } from '../../models/sprint/sprint';
import { Task } from '../../models/task/task';
import { TaskService } from '../../services/task/task.service';

@Component({
  selector: 'app-backlog',
  templateUrl: './backlog.component.html',
  styleUrls: ['./backlog.component.scss'],
})
export class BacklogComponent {
  tasks: Observable<Task[]>;

  displayedColumns = [
    'actions',
    'title',
    'assignedTo',
    'assignmentDate',
    'endDate',
    'effort',
    'status',
  ];

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

  loadTasks() {
    this.tasks = this.taskService.list();
  }

  onAdd() {
    let _modal = this.dialog.open(TaskFormModalComponent, {});

    _modal.afterClosed().subscribe((task) => {
      this.loadTasks();
    });
  }

  onEdit(task: Task) {
    let _modal = this.dialog.open(TaskFormModalComponent, {
      data: task,
    });

    _modal.afterClosed().subscribe((task) => {
      this.loadTasks();
    });
  }
}
