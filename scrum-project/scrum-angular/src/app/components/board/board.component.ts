import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map, mergeMap, combineLatestWith } from 'rxjs';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

import { Sprint } from '../../models/sprint/sprint';
import { Task } from '../../models/task/task';
import { TaskService } from '../../services/task/task.service';
import { Status } from 'src/app/enum/status.enum';
import { SprintService } from 'src/app/services/sprint/sprint.service';
import { ProjectService } from 'src/app/services/project/project.service';
import { Project } from 'src/app/models/project/project';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
})
export class BoardComponent implements OnInit {
  tasks: Observable<Task[]> = new Observable<Task[]>();
  sprints: Observable<Sprint[]> = new Observable<Sprint[]>();
  projects: Observable<Project[]> = new Observable<Project[]>();
  todo: Task[] = [];
  inprogress: Task[] = [];
  done: Task[] = [];
  selectedSprintId: string | undefined = undefined;

  constructor(
    private taskService: TaskService,
    private sprintService: SprintService,
    private projectService: ProjectService,
    private authService: AuthService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {
    this.tasks = this.taskService.list();
  }

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
    this.snackBar.open('Status da tarefa aualizado!', 'X', {
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
    this.tasks = this.taskService.list().pipe(
      combineLatestWith(this.sprints),
      map(([allTasks, loadedSprints]) => {
        return allTasks.filter(
          (task) =>
            loadedSprints.some(
              (loadedSprint) => task.sprint.id === loadedSprint.id
            ) && task.status !== Status.Disabled
        );
      })
    );

    this.tasks.subscribe((tasks) => {
      this.todo = tasks.filter(
        (task) =>
          task.sprint.id === this.selectedSprintId &&
          task.status === Status.ToDo
      );
      this.inprogress = tasks.filter(
        (task) =>
          task.sprint.id === this.selectedSprintId &&
          task.status === Status.InProgress
      );
      this.done = tasks.filter(
        (task) =>
          task.sprint.id === this.selectedSprintId &&
          task.status === Status.Done
      );
    });
  }

  loadSprints() {
    this.sprints = this.sprintService.list().pipe(
      combineLatestWith(this.projects),
      map(([allSprints, loadedProjects]) => {
        const filteredSprints = allSprints.filter((sprint) =>
          loadedProjects.some(
            (loadedProject) => sprint.project.id === loadedProject.id
          )
        );

        if (this.selectedSprintId === undefined && filteredSprints.length > 0) {
          this.selectedSprintId = filteredSprints[0].id;
        }

        return filteredSprints;
      })
    );
  }

  loadProjects() {
    this.projects = this.authService.getCurrentUser().pipe(
      mergeMap((user) => {
        const memberProjects = user.memberProjects || [];
        const reporterProjects = user.reporterProjects || [];

        const distinctProjects = [
          ...memberProjects,
          ...reporterProjects.filter(
            (reporterProject) =>
              !memberProjects.some(
                (memberProject) => memberProject.id === reporterProject.id
              )
          ),
        ];

        return this.projectService.list().pipe(
          first(),
          map((allProjects) => {
            return allProjects.filter((project) =>
              distinctProjects.some(
                (distinctProject) => distinctProject.id === project.id
              )
            );
          })
        );
      })
    );
  }

  onSprintChange() {
    this.loadTasks();
  }

  ngOnInit() {
    this.loadProjects();
    this.loadSprints();
    this.loadTasks();
  }
}
