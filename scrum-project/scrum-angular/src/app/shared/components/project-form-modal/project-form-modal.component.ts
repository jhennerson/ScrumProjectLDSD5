import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogContent, MatDialogActions, MatDialogClose } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first } from 'rxjs';
import { Project } from 'src/app/models/project/project';
import { User } from 'src/app/models/user/user';
import { ProjectService } from 'src/app/services/project/project.service';
import { UserService } from 'src/app/services/user/user.service';
import { CdkScrollable } from '@angular/cdk/scrolling';
import { MatFormField, MatLabel, MatSuffix } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatSelect } from '@angular/material/select';
import { MatOption } from '@angular/material/core';
import { NgFor, AsyncPipe } from '@angular/common';
import { MatDatepickerInput, MatDatepickerToggle, MatDatepicker } from '@angular/material/datepicker';
import { MatFabButton } from '@angular/material/button';

@Component({
    selector: 'app-project-form-modal',
    templateUrl: './project-form-modal.component.html',
    styleUrls: ['./project-form-modal.component.scss'],
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
export class ProjectFormModalComponent {
  form: FormGroup;
  users: Observable<User[]> = new Observable<User[]>();

  constructor(
    private formBuilder: FormBuilder,
    private projectService: ProjectService,
    private userService: UserService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: Project
  ) {
    this.form = this.formBuilder.group({
      id: ['', [Validators.required]],
      title: ['', [Validators.required]],
      reporter: ['', [Validators.required]],
      assignmentDate: [''],
      endDate: [''],
      members: [''],
    });

    if (data) {
      this.form.patchValue({
        id: data.id,
        title: data.title,
        reporter: data.reporter,
        assignmentDate: data.assignmentDate,
        endDate: data.endDate,
        members: data.members,
      });
    }
  }

  onSubmit() {
    this.projectService.save(this.form.value).subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError(),
    });
  }

  private onSuccess() {
    this.snackBar.open('Projeto salvo com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'success-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar projeto!', 'X', {
      duration: 3000,
      panelClass: 'error-snackbar',
    });
  }

  loadUsers() {
    this.users = this.userService.list().pipe(first());
  }

  ngOnInit(): void {
    this.loadUsers();
  }
}
