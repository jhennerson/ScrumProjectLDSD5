import { MatDialog } from '@angular/material/dialog';
import { SprintService } from './../../services/sprint/sprint.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map, mergeMap, combineLatest } from 'rxjs';
import { Sprint } from 'src/app/models/sprint/sprint';
import { SprintFormModalComponent } from 'src/app/shared/components/sprint-form-modal/sprint-form-modal.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { Project } from 'src/app/models/project/project';
import { ProjectService } from 'src/app/services/project/project.service';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-sprint',
  templateUrl: './sprint.component.html',
  styleUrls: ['./sprint.component.scss'],
})
export class SprintComponent implements OnInit {
  sprints: Observable<Sprint[]> = new Observable<Sprint[]>();
  projects: Observable<Project[]> = new Observable<Project[]>();
  selectedProjectId: string | undefined = undefined;

  loadedProjects: Project[] = [];

  displayedColumns = [
    'title',
    'project',
    'reporter',
    'description',
    'assignmentDate',
    'endDate',
    'actions',
  ];

  constructor(
    private sprintService: SprintService,
    private authService: AuthService,
    private projectService: ProjectService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {}

  onAdd() {
    const dialogRef = this.dialog.open(SprintFormModalComponent, {});

    dialogRef.afterClosed().subscribe(() => {
      this.loadSprints();
    });
  }

  onEdit(sprint: Sprint) {
    const dialogRef = this.dialog.open(SprintFormModalComponent, {
      data: sprint,
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadSprints();
    });
  }

  onDelete(sprint: Sprint) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Deseja apagar essa sprint permanentemente?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.sprintService.remove(sprint.id).subscribe({
          next: () => {
            this.onHardDelete();
            this.ngOnInit();
          },
          error: () => this.onError(),
        });
      }
    });
  }

  private onHardDelete() {
    this.snackBar.open('Sprint apagada com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'success-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao apagar sprint!', 'X', {
      duration: 3000,
      panelClass: 'error-snackbar',
    });
  }

  loadSprints() {
    if (!this.selectedProjectId) {
      this.sprints = new Observable<Sprint[]>();
      return;
    }

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
            if (allProjects.length > 0) {
              this.selectedProjectId = allProjects[0].id;
              this.loadedProjects = allProjects;
              this.loadSprints();
            }
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

  onProjectChange() {
    this.loadSprints();
  }

  ngOnInit() {
    this.loadProjects();
  }
}
