import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map } from 'rxjs';
import { Status } from 'src/app/enum/status.enum';
import { Sprint } from 'src/app/models/sprint/sprint';
import { Task } from 'src/app/models/task/task';
import { TaskService } from 'src/app/services/task/task.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

@Component({
  selector: 'app-waste-bin',
  templateUrl: './waste-bin.component.html',
  styleUrls: ['./waste-bin.component.scss'],
})
export class WasteBinComponent {
  disabledTasks: Observable<Task[]>;

  displayedColumns = [
    'title',
    'assignee',
    'reporter',
    'assignmentDate',
    'endDate',
    'storyPoints',
    'userStory',
    'status',
    'actions',
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
    const dialogRef = this.dialog.open(TaskFormModalComponent, {});

    dialogRef.afterClosed().subscribe(() => {
      this.loadTasks();
    });
  }

  onEdit(task: Task) {
    const dialogRef = this.dialog.open(TaskFormModalComponent, {
      data: {
        task: task,
        enableable: true,
      },
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadTasks();
    });
  }

  onRestore(task: Task) {
    task.status = Status.ToDo;
    this.updateTaskStatus(task);
  }

  onDelete(task: Task) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Deseja apagar essa tarefa permanentemente?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.taskService.remove(task.id).subscribe({
          next: () => {
            this.onHardDelete();
            this.loadTasks();
          },
          error: () => this.onError(),
        });
      }
    });
  }

  private updateTaskStatus(task: Task) {
    this.taskService.save(task).subscribe({
      next: () => {
        this.onSuccess();
        this.loadTasks();
      },
      error: () => this.onError(),
    });
  }

  private onSuccess() {
    this.snackBar.open('Tarefa restaurada com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  private onHardDelete() {
    this.snackBar.open('Tarefa apagada com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao restaurar tarefa!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  loadTasks() {
    this.disabledTasks = this.taskService.list().pipe(
      first(),
      map((tasks) => tasks.filter((task) => task.status === Status.Disabled))
    );
  }

  ngOnInit() {
    this.loadTasks();
  }
}
