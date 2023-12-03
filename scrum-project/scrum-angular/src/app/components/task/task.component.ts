import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map, combineLatest, mergeMap } from 'rxjs';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';
import { SprintService } from './../../services/sprint/sprint.service';

import { Status } from 'src/app/enum/status.enum';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { Sprint } from '../../models/sprint/sprint';
import { Task } from '../../models/task/task';
import { TaskService } from '../../services/task/task.service';
import { Project } from 'src/app/models/project/project';
import { ProjectService } from 'src/app/services/project/project.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss'],
})
export class TaskComponent implements OnInit {
  tasks: Observable<Task[]> = new Observable<Task[]>();
  sprints: Observable<Sprint[]> = new Observable<Sprint[]>();
  projects: Observable<Project[]> = new Observable<Project[]>();
  selectedSprintId = 'undefined';

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
    private projectService: ProjectService,
    private authService: AuthService,
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
    this.tasks = combineLatest([this.taskService.list(), this.sprints]).pipe(
      map(([allTasks, loadedSprints]) => {
        return allTasks.filter(
          (task) =>
            loadedSprints.some(
              (loadedSprint) => task.sprint.id === loadedSprint.id
            ) && task.status !== Status.Disabled
        );
      })
    );
  }

  loadSprints() {
    this.sprints = combineLatest([
      this.sprintService.list(),
      this.projects,
    ]).pipe(
      map(([allSprints, loadedProjects]) => {
        return allSprints.filter((sprint) =>
          loadedProjects.some(
            (loadedProject) => sprint.project.id === loadedProject.id
          )
        );
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
