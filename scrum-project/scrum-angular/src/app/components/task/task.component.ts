import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map } from 'rxjs';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';
import { SprintService } from './../../services/sprint/sprint.service';

import { Status } from 'src/app/enum/status.enum';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { Sprint } from '../../models/sprint/sprint';
import { Task } from '../../models/task/task';
import { TaskService } from '../../services/task/task.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss'],
})
export class TaskComponent implements OnInit {
  tasks: Observable<Task[]> = new Observable<Task[]>();
  sprintOptions: Sprint[] = [];
  selectedSprintId: string | undefined;

  displayedColumns = [
    'title',
    'assignee',
    'reporter',
    'sprint',
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

  onAdd() {
    let modal = this.dialog.open(TaskFormModalComponent, {});

    modal.afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }

  onEdit(task: Task) {
    const dialogRef = this.dialog.open(TaskFormModalComponent, {
      data: task,
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadTasks();
    });
  }

  onDisable(task: Task) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Deseja realmente apagar essa tarefa?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        task.status = Status.Disabled;
        this.updateTaskStatus(task);
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
    this.snackBar.open('Tarefa salva com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'success-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar tarefa!', 'X', {
      duration: 3000,
      panelClass: 'error-snackbar',
    });
  }

  loadTasks() {
    this.tasks = this.taskService.list().pipe(
      first(),
      map((tasks) => {
        if (this.selectedSprintId !== undefined) {
          return tasks.filter(
            (task) =>
              task.sprint.id === this.selectedSprintId &&
              task.status !== Status.Disabled
          );
        }
        return tasks.filter((task) => task.status !== Status.Disabled);
      })
    );
  }

  loadSprints() {
    this.sprintService.list().subscribe((options) => {
      this.sprintOptions = options;

      if (this.sprintOptions.length > 0) {
        this.selectedSprintId = this.sprintOptions[0].id;
        this.loadTasks();
      }
    });
  }

  onSprintChange() {
    this.loadTasks();
  }

  ngOnInit() {
    this.loadTasks();
    this.loadSprints();
  }
}
