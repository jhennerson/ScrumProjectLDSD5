import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map } from 'rxjs';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

import { Sprint } from '../../models/sprint/sprint';
import { Task } from '../../models/task/task';
import { TaskService } from '../../services/task/task.service';

@Component({
  selector: 'app-backlog',
  templateUrl: './backlog.component.html',
  styleUrls: ['./backlog.component.scss'],
})
export class BacklogComponent implements OnInit {
  enabledTasks: Observable<Task[]>;

  displayedColumns = [
    'actions',
    'title',
    'assignedTo',
    'assignmentDate',
    'endDate',
    'storyPoints',
    'userStory',
    'status',
  ];

  constructor(
    private taskService: TaskService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {
    this.enabledTasks = this.taskService.list().pipe(first());
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
    let _modal = this.dialog.open(TaskFormModalComponent, {});

    _modal.afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }

  onEdit(task: Task) {
    let _modal = this.dialog.open(TaskFormModalComponent, {
      data: {
        task: task,
        enableable: false,
      },
    });

    _modal.afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }

  loadTasks() {
    this.enabledTasks = this.taskService.list().pipe(
      first(),
      map((tasks) => tasks.filter((task) => task.status !== 'DISABLED'))
    );
  }

  ngOnInit() {
    this.loadTasks();
  }
}
