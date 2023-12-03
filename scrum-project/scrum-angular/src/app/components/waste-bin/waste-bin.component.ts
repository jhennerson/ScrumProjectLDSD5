import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map, combineLatest, mergeMap } from 'rxjs';
import { Status } from 'src/app/enum/status.enum';
import { Project } from 'src/app/models/project/project';
import { Sprint } from 'src/app/models/sprint/sprint';
import { Task } from 'src/app/models/task/task';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ProjectService } from 'src/app/services/project/project.service';
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
  sprints: Observable<Sprint[]> = new Observable<Sprint[]>();
  projects: Observable<Project[]> = new Observable<Project[]>();
  selectedSprintId = 'undefined';

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
    private projectService: ProjectService,
    private authService: AuthService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {}

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

  private onHardDelete() {
    this.snackBar.open('Tarefa apagada com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'success-snackbar',
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

  private onError() {
    this.snackBar.open('Erro ao restaurar tarefa!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  loadTasks() {
    this.disabledTasks = combineLatest([
      this.taskService.list(),
      this.sprints,
    ]).pipe(
      map(([allTasks, loadedSprints]) => {
        return allTasks.filter(
          (disabledTask) =>
            loadedSprints.some(
              (loadedSprint) => disabledTask.sprint.id === loadedSprint.id
            ) && disabledTask.status === Status.Disabled
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
