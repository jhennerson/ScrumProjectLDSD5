import { Observable, first, map, mergeMap } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/models/project/project';
import { ProjectService } from 'src/app/services/project/project.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectFormModalComponent } from 'src/app/shared/components/project-form-modal/project-form-modal.component';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss'],
})
export class ProjectComponent implements OnInit {
  projects: Observable<Project[]> = new Observable<Project[]>();

  constructor(
    private projectService: ProjectService,
    private authService: AuthService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {}

  onAdd() {
    const dialogRef = this.dialog.open(ProjectFormModalComponent, {});

    dialogRef.afterClosed().subscribe(() => {
      this.loadProjects();
    });
  }

  onEdit(project: Project) {
    const dialogRef = this.dialog.open(ProjectFormModalComponent, {
      data: project,
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadProjects();
    });
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
          map((allProjects) =>
            allProjects.filter((project) =>
              distinctProjects.some(
                (distinctProject) => distinctProject.id === project.id
              )
            )
          )
        );
      })
    );
  }

  ngOnInit() {
    this.loadProjects();
  }
}
