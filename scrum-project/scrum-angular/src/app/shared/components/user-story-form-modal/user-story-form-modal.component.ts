import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Project } from 'src/app/models/project/project';
import { UserStory } from 'src/app/models/user-story/user-story';
import { User } from 'src/app/models/user/user';
import { ProjectService } from 'src/app/services/project/project.service';
import { UserStoryService } from 'src/app/services/user-story/user-story.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-user-story-modal',
  templateUrl: './user-story-form-modal.component.html',
  styleUrls: ['./user-story-form-modal.component.scss'],
})
export class UserStoryFormModalComponent implements OnInit {
  form: FormGroup;

  projects: Observable<Project[]> = new Observable<Project[]>();

  userOptions: User[] = [];
  projectOptions: Project[] = [];

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
      panelClass: 'task-status-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar história do usuário!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
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

  ngOnInit() {
    this.loadUsers();
    this.loadProjects();
  }
}
