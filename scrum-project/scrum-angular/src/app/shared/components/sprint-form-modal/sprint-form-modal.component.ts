import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Project } from 'src/app/models/project/project';
import { Sprint } from 'src/app/models/sprint/sprint';
import { User } from 'src/app/models/user/user';
import { ProjectService } from 'src/app/services/project/project.service';
import { UserService } from 'src/app/services/user/user.service';
import { SprintService } from './../../../services/sprint/sprint.service';

@Component({
  selector: 'app-sprint-form-modal',
  templateUrl: './sprint-form-modal.component.html',
  styleUrls: ['./sprint-form-modal.component.scss'],
})
export class SprintFormModalComponent implements OnInit {
  form: FormGroup;

  userOptions: User[] = [];
  projectOptions: Project[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private sprintService: SprintService,
    private projectService: ProjectService,
    private userService: UserService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: Sprint
  ) {
    this.form = this.formBuilder.group({
      id: ['', [Validators.required]],
      title: ['', [Validators.required]],
      project: ['', [Validators.required]],
      reporter: ['', [Validators.required]],
      assignmentDate: ['', [Validators.required]],
      endDate: ['', [Validators.required]],
      description: [''],
    });

    if (data) {
      this.form.patchValue({
        id: data.id,
        title: data.title,
        project: data.project,
        reporter: data.reporter,
        assignmentDate: data.assignmentDate,
        endDate: data.endDate,
        description: data.description,
      });
    }
  }

  onSubmit() {
    this.sprintService.save(this.form.value).subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError(),
    });
  }

  private onSuccess() {
    this.snackBar.open('Sprint salva com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'sprint-status-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar sprint!', 'X', {
      duration: 2000,
      panelClass: 'sprint-status-snackbar',
    });
  }

  loadUsers() {
    this.userService
      .list()
      .subscribe((options) => (this.userOptions = options));
  }

  loadProjects() {
    this.projectService.list().subscribe((options) => {
      this.projectOptions = options;
    });
  }

  ngOnInit(): void {
    this.loadUsers();
    this.loadProjects();
  }
}
