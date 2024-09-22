import { Observable, first } from 'rxjs';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogContent, MatDialogActions, MatDialogClose } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Project } from 'src/app/models/project/project';
import { Sprint } from 'src/app/models/sprint/sprint';
import { User } from 'src/app/models/user/user';
import { ProjectService } from 'src/app/services/project/project.service';
import { UserService } from 'src/app/services/user/user.service';
import { SprintService } from './../../../services/sprint/sprint.service';
import { CdkScrollable } from '@angular/cdk/scrolling';
import { MatFormField, MatLabel, MatSuffix } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatSelect } from '@angular/material/select';
import { MatOption } from '@angular/material/core';
import { NgFor, AsyncPipe } from '@angular/common';
import { MatDatepickerInput, MatDatepickerToggle, MatDatepicker } from '@angular/material/datepicker';
import { MatFabButton } from '@angular/material/button';

@Component({
    selector: 'app-sprint-form-modal',
    templateUrl: './sprint-form-modal.component.html',
    styleUrls: ['./sprint-form-modal.component.scss'],
    standalone: true,
    imports: [
        CdkScrollable,
        MatDialogContent,
        ReactiveFormsModule,
        MatFormField,
        MatLabel,
        MatInput,
        MatSelect,
        MatOption,
        NgFor,
        MatDatepickerInput,
        MatDatepickerToggle,
        MatSuffix,
        MatDatepicker,
        MatDialogActions,
        MatFabButton,
        MatDialogClose,
        AsyncPipe,
    ],
})
export class SprintFormModalComponent implements OnInit {
  form: FormGroup;

  users: Observable<User[]> = new Observable<User[]>();
  projects: Observable<Project[]> = new Observable<Project[]>();

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
      panelClass: 'success-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar sprint!', 'X', {
      duration: 3000,
      panelClass: 'error-snackbar',
    });
  }

  loadUsers() {
    this.users = this.userService.list().pipe(first());
  }

  loadProjects() {
    this.projects = this.projectService.list().pipe(first());
  }

  ngOnInit(): void {
    this.loadUsers();
    this.loadProjects();
  }
}
