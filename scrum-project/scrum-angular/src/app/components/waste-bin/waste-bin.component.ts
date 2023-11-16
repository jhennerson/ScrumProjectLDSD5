import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map } from 'rxjs';
import { Status } from 'src/app/enum/status.enum';
import { Sprint } from 'src/app/models/sprint/sprint';
import { Task } from 'src/app/models/task/task';
import { SprintService } from 'src/app/services/sprint/sprint.service';
import { TaskService } from 'src/app/services/task/task.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

@Component({
  selector: 'app-waste-bin',
  templateUrl: './waste-bin.component.html',
  styleUrls: ['./waste-bin.component.scss'],
})
export class WasteBinComponent {
  disabledTasks: Observable<Task[]> = new Observable<Task[]>();
  sprintOptions: Sprint[] = [];
  selectedSprintId: string | undefined;

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
    private sprintService: SprintService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {}

  sprints: Sprint[] = [];

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
      map((tasks) => {
        if (this.selectedSprintId !== undefined) {
          return tasks.filter(
            (task) =>
              task.sprint.id === this.selectedSprintId &&
              task.status === Status.Disabled
          );
        }
        return tasks.filter((task) => task.status === Status.Disabled);
      })
    );
  }

  loadSprints() {
    this.sprintService
      .list()
      .subscribe((options) => (this.sprintOptions = options));
  }

  onSprintChange() {
    this.loadTasks();
  }

  ngOnInit() {
    this.loadTasks();
    this.loadSprints();
  }
}
