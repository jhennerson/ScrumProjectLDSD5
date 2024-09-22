import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogContent, MatDialogActions, MatDialogClose } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first } from 'rxjs';
import { Project } from 'src/app/models/project/project';
import { UserStory } from 'src/app/models/user-story/user-story';
import { User } from 'src/app/models/user/user';
import { ProjectService } from 'src/app/services/project/project.service';
import { UserStoryService } from 'src/app/services/user-story/user-story.service';
import { UserService } from 'src/app/services/user/user.service';
import { CdkScrollable } from '@angular/cdk/scrolling';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatSelect } from '@angular/material/select';
import { MatOption } from '@angular/material/core';
import { NgFor, AsyncPipe } from '@angular/common';
import { MatFabButton } from '@angular/material/button';

@Component({
    selector: 'app-user-story-modal',
    templateUrl: './user-story-form-modal.component.html',
    styleUrls: ['./user-story-form-modal.component.scss'],
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
        MatDialogActions,
        MatFabButton,
        MatDialogClose,
        AsyncPipe,
    ],
})
export class UserStoryFormModalComponent implements OnInit {
  form: FormGroup;

  projects: Observable<Project[]> = new Observable<Project[]>();
  users: Observable<User[]> = new Observable<User[]>();

  constructor(
    private formBuilder: FormBuilder,
    private userStoryService: UserStoryService,
    private userService: UserService,
    private projectService: ProjectService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: UserStory
  ) {
    this.form = this.formBuilder.group({
      id: ['', [Validators.required]],
      title: ['', Validators.required],
      project: ['', [Validators.required]],
      assignee: ['', [Validators.required]],
      reporter: ['', [Validators.required]],
      description: [''],
    });

    if (data) {
      this.form.patchValue({
        id: data.id,
        title: data.title,
        project: data.project,
        assignee: data.assignee,
        reporter: data.reporter,
        description: data.description,
      });
    }
  }

  onSubmit() {
    this.userStoryService.save(this.form?.value).subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError(),
    });
  }

  private onSuccess() {
    this.snackBar.open('História do usuário salva com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'success-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar história do usuário!', 'X', {
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

  ngOnInit() {
    this.loadUsers();
    this.loadProjects();
  }
}
