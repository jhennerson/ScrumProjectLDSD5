import { Observable } from 'rxjs';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/models/user/user';
import { UserService } from 'src/app/services/user/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Project } from 'src/app/models/project/project';
import { ProjectService } from 'src/app/services/project/project.service';

@Component({
  selector: 'app-project-form-modal',
  templateUrl: './project-form-modal.component.html',
  styleUrls: ['./project-form-modal.component.scss'],
})
export class ProjectFormModalComponent {
  form: FormGroup;
  users: Observable<User[]> = new Observable<User[]>();

  userOptions: User[] = [];

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
    });

    if (data) {
      this.form.patchValue({
        id: data.id,
        title: data.title,
        assignmentDate: data.assignmentDate,
        endDate: data.endDate,
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
      panelClass: 'sprint-status-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar projeto!', 'X', {
      duration: 2000,
      panelClass: 'sprint-status-snackbar',
    });
  }

  loadUsers() {
    this.userService
      .list()
      .subscribe((options) => (this.userOptions = options));
  }

  ngOnInit(): void {
    this.loadUsers();
  }
}
