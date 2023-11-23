import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first } from 'rxjs';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

import { Sprint } from '../../models/sprint/sprint';
import { Task } from '../../models/task/task';
import { TaskService } from '../../services/task/task.service';
import { Status } from 'src/app/enum/status.enum';
import { SprintService } from 'src/app/services/sprint/sprint.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
})
export class BoardComponent implements OnInit {
  tasks: Observable<Task[]> = new Observable<Task[]>();
  sprintOptions: Sprint[] = [];
  todo: Task[] = [];
  inprogress: Task[] = [];
  done: Task[] = [];
  selectedSprintId: string | undefined;

  constructor(
    private taskService: TaskService,
    private sprintService: SprintService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {
    this.tasks = this.taskService.list();
  }

  sprints: Sprint[] = [];

  drop(event: CdkDragDrop<Task[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );

      let task = event.container.data[event.currentIndex];
      task.status = event.container.id;

      this.updateTaskStatus(task);
    }
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
      panelClass: 'task-status-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar tarefa!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  onAdd() {
    const dialogRef = this.dialog.open(TaskFormModalComponent, {});

    dialogRef.afterClosed().subscribe(() => {
      this.loadTasks();
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

  loadTasks() {
    this.tasks.subscribe((tasks) => {
      if (this.selectedSprintId !== undefined) {
        tasks = tasks.filter(
          (task) => task.sprint.id === this.selectedSprintId
        );
      }

      this.todo = tasks.filter((task) => task.status === Status.ToDo);
      this.inprogress = tasks.filter(
        (task) => task.status === Status.InProgress
      );
      this.done = tasks.filter((task) => task.status === Status.Done);
    });
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
