import { Observable, first } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/models/project/project';
import { ProjectService } from 'src/app/services/project/project.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectFormModalComponent } from 'src/app/shared/components/project-form-modal/project-form-modal.component';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss'],
})
export class ProjectComponent implements OnInit {
  projects: Observable<Project[]> = new Observable<Project[]>();

  constructor(
    private projectService: ProjectService,
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
    this.projects = this.projectService.list().pipe(first());
  }

  ngOnInit() {
    this.loadProjects();
  }
}
