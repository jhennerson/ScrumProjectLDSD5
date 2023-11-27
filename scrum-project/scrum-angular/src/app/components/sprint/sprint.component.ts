import { MatDialog } from '@angular/material/dialog';
import { SprintService } from './../../services/sprint/sprint.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map } from 'rxjs';
import { Sprint } from 'src/app/models/sprint/sprint';
import { SprintFormModalComponent } from 'src/app/shared/components/sprint-form-modal/sprint-form-modal.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { Project } from 'src/app/models/project/project';
import { ProjectService } from 'src/app/services/project/project.service';

@Component({
  selector: 'app-sprint',
  templateUrl: './sprint.component.html',
  styleUrls: ['./sprint.component.scss'],
})
export class SprintComponent implements OnInit {
  sprints: Observable<Sprint[]> = new Observable<Sprint[]>();
  projectOptions: Project[] = [];
  selectedProjectId: string | undefined;

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
    this.sprints = this.sprintService.list().pipe(
      first(),
      map((sprints) => {
        if (this.selectedProjectId !== undefined) {
          return sprints.filter(
            (sprint) => sprint.project.id === this.selectedProjectId
          );
        }
        return sprints;
      })
    );
  }

  loadProjects() {
    this.projectService.list().subscribe((options) => {
      this.projectOptions = options;

      if (this.projectOptions.length > 0) {
        this.selectedProjectId = this.projectOptions[0].id;
        this.loadSprints();
      }
    });
  }

  onProjectChange() {
    this.loadSprints();
  }

  ngOnInit() {
    this.loadSprints();
    this.loadProjects();
  }
}
