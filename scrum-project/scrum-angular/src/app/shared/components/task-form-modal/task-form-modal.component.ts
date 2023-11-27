import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Status } from 'src/app/enum/status.enum';
import { Sprint } from 'src/app/models/sprint/sprint';
import { Task } from 'src/app/models/task/task';
import { UserStory } from 'src/app/models/user-story/user-story';
import { User } from 'src/app/models/user/user';
import { SprintService } from 'src/app/services/sprint/sprint.service';
import { TaskService } from 'src/app/services/task/task.service';
import { UserStoryService } from 'src/app/services/user-story/user-story.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-task-form-modal',
  templateUrl: './task-form-modal.component.html',
  styleUrls: ['./task-form-modal.component.scss'],
})
export class TaskFormModalComponent implements OnInit {
  form: FormGroup;

  userOptions: User[] = [];
  userStoryOptions: UserStory[] = [];
  sprintOptions: Sprint[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private taskService: TaskService,
    private userService: UserService,
    private sprintService: SprintService,
    private userStoryService: UserStoryService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: Task
  ) {
    this.form = this.formBuilder.group({
      id: ['', [Validators.required]],
      title: ['', [Validators.required]],
      sprint: ['', [Validators.required]],
      userStory: ['', [Validators.required]],
      assignee: ['', [Validators.required]],
      reporter: ['', [Validators.required]],
      storyPoints: [''],
      assignmentDate: ['', [Validators.required]],
      endDate: ['', [Validators.required]],
      description: [''],
      status: [Status.ToDo],
    });

    if (data) {
      this.form.patchValue({
        id: data.id,
        title: data.title,
        sprint: data.sprint,
        userStory: data.userStory,
        assignee: data.assignee,
        reporter: data.reporter,
        storyPoints: data.storyPoints,
        assignmentDate: data.assignmentDate,
        endDate: data.endDate,
        description: data.description,
        status: data.status,
      });
    }
  }

  onSubmit() {
    this.taskService.save(this.form.value).subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError(),
    });
  }

  onRestore() {
    this.form.patchValue({ status: 'TO_DO' });
  }

  private onSuccess() {
    this.snackBar.open('Tarefa salva com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'success-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar tarefa!', 'X', {
      duration: 3000,
      panelClass: 'error-snackbar',
    });
  }

  loadUsers() {
    this.userService
      .list()
      .subscribe((options) => (this.userOptions = options));
  }

  loadUserStories() {
    this.userStoryService
      .list()
      .subscribe((options) => (this.userStoryOptions = options));
  }

  loadSprints() {
    this.sprintService
      .list()
      .subscribe((options) => (this.sprintOptions = options));
  }

  ngOnInit() {
    this.loadUsers();
    this.loadUserStories();
    this.loadSprints();
  }
}
