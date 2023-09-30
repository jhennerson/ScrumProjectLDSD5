import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

import { Sprint } from '../../models/sprint/sprint';
import { Task } from '../../models/task/task';
import { TaskService } from '../../services/task/task.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
})
export class BoardComponent implements OnInit {
  tasks: Observable<Task[]>;

  todo: Task[] = [];
  inprogress: Task[] = [];
  done: Task[] = [];

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
      next: () => this.onSuccess(),
      error: () => this.onError(),
    });
  }

  private onSuccess() {
    this.snackBar.open('Tarefa salva com sucesso!', 'X', { duration: 2000 });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar tarefa!', 'X', { duration: 2000 });
  }

  onAdd() {
    this.dialog.open(TaskFormModalComponent, {
      width: `80%`,
      height: `80%`,
    });
  }

  onEdit(task: Task) {
    this.dialog.open(TaskFormModalComponent, {
      width: `80%`,
      height: `80%`,
      data: {
        id: task.id,
        title: task.title,
        userId: task.userId,
        assignmentDate: task.assignmentDate,
        endDate: task.endDate,
        effort: task.effort,
        description: task.description,
        status: task.status,
      },
    });
  }

  ngOnInit() {
    this.tasks.subscribe((tasks) => {
      this.todo = tasks.filter((task) => task.status === 'TO_DO');
      this.inprogress = tasks.filter((task) => task.status === 'IN_PROGRESS');
      this.done = tasks.filter((task) => task.status === 'DONE');
    });
  }
}
