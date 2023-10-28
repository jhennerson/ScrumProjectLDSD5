import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map } from 'rxjs';
import { Sprint } from 'src/app/models/sprint/sprint';
import { Task } from 'src/app/models/task/task';
import { TaskService } from 'src/app/services/task/task.service';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

@Component({
  selector: 'app-waste-bin',
  templateUrl: './waste-bin.component.html',
  styleUrls: ['./waste-bin.component.scss'],
})
export class WasteBinComponent {
  disabledTasks: Observable<Task[]>;

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
    this.disabledTasks = this.taskService.list().pipe(first());
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
        enableable: true,
      },
    });

    _modal.afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }

  loadTasks() {
    this.disabledTasks = this.taskService.list().pipe(
      first(),
      map((tasks) => tasks.filter((task) => task.status === 'DISABLED'))
    );
  }

  ngOnInit() {
    this.loadTasks();
  }
}
