import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';

import { Sprint } from '../../sprints/model/sprint';
import { Task } from '../model/task';
import { TasksService } from '../services/tasks.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss']
})

export class TasksComponent implements OnInit{

  tasks: Observable<Task[]>;
  todo: Task[] = [];
  inprogress: Task[] = [];
  done: Task[] = [];

  constructor(private tasksService: TasksService,
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog,
    public snackBar: MatSnackBar) {
    this.tasks = this.tasksService.list();
  }

  sprints: Sprint[] = [{
    id: '1',
    title: 'Sprint atual',
    assignmentDate: new Date(),
    endDate: new Date(),
    description: 'Sprint atual',
    status: 'Em execução'
  }];

  drop(event: CdkDragDrop<Task[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );

      let task = event.container.data[event.currentIndex];
      task.status = event.container.id;

      this.updateTaskStatus(task);
    }
  }

  private updateTaskStatus(task: Task) {
    this.tasksService.save(task).subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError()
    });
  }

  private onSuccess() {
    this.snackBar.open('Task salva com sucesso!', 'X', {duration: 2000});
  }

  private onError() {
    this.snackBar.open('Erro ao salvar task!', 'X', {duration: 2000});
  }

  ngOnInit() {
    this.tasks.subscribe(tasks => {
      this.todo = tasks.filter(task => task.status === 'TO_DO');
      this.inprogress = tasks.filter(task => task.status === 'IN_PROGRESS');
      this.done = tasks.filter(task => task.status === 'DONE');
    });
  }
}
