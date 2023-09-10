import { Component } from '@angular/core';
import { Task } from '../model/task';
import { Sprint } from '../../sprints/model/sprint'
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { TasksService } from '../services/tasks.service';
import { Observable, tap } from 'rxjs';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss']
})

export class TasksComponent {

  tasks: Observable<Task[]>;
  todo: Task[] = [];
  inprogress: Task[] = [];
  done: Task[] = [];

  constructor(private tasksService: TasksService) {
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
    }
  }

  ngOnInit() {
    this.tasks.subscribe(tasks => {
      this.todo = tasks.filter(task => task.status === 'TO_DO');
      this.inprogress = tasks.filter(task => task.status === 'IN_PROGRESS');
      this.done = tasks.filter(task => task.status === 'DONE');
    });
  }
}
