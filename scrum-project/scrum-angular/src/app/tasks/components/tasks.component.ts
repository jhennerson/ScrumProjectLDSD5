import { Component } from '@angular/core';
import { Task } from '../model/task';
import { Sprint } from '../../sprints/model/sprint'
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss']
})

export class TasksComponent {

  sprintAtual: Sprint = {
    id: '1',
    title: 'Sprint atual',
    assignmentDate: new Date("2023-01-01"),
    endDate: new Date("2023-01-15"),
    description: 'Sprint atual',
    status: 'Em execução'
  }
  sprints: Sprint[] = [this.sprintAtual];
  tasks: Task[] = [];

  task1: Task = {
    id: '1',
    title: 'Task 1',
    user: 'User 1',
    assignmentDate: new Date("2023-01-01"),
    endDate: new Date("2023-01-15"),
    effort: 5,
    description: "Realizar a tarefa",
    status: "todo"
  }

  task2: Task = {
    id: '2',
    title: 'Task 2',
    user: 'User 2',
    assignmentDate: new Date("2023-02-02"),
    endDate: new Date("2023-02-15"),
    effort: 5,
    description: "Realizar a tarefa 2",
    status: "todo"
  }

  task3: Task = {
    id: '3',
    title: 'Task 3',
    user: 'User 3',
    assignmentDate: new Date("2023-03-03"),
    endDate: new Date("2023-03-15"),
    effort: 5,
    description: "Realizar a tarefa 3",
    status: "todo"
  }

  task4: Task = {
    id: '1',
    title: 'Task 1',
    user: 'User 1',
    assignmentDate: new Date("2023-01-01"),
    endDate: new Date("2023-01-15"),
    effort: 5,
    description: "Realizar a tarefa",
    status: "todo"
  }

  task5: Task = {
    id: '1',
    title: 'Task 1',
    user: 'User 1',
    assignmentDate: new Date("2023-01-01"),
    endDate: new Date("2023-01-15"),
    effort: 5,
    description: "Realizar a tarefa",
    status: "todo"
  }

  task6: Task = {
    id: '1',
    title: 'Task 1',
    user: 'User 1',
    assignmentDate: new Date("2023-01-01"),
    endDate: new Date("2023-01-15"),
    effort: 5,
    description: "Realizar a tarefa",
    status: "todo"
  }

  todo: Task[] = [this.task1, this.task4, this.task5, this.task6];
  inprogress: Task[] = [this.task3];
  done: Task[] = [this.task2];

  constructor() {
    this.tasks = [];
  }

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
}
