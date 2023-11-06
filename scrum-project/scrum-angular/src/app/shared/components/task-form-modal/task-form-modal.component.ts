import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Task } from 'src/app/models/task/task';
import { UserStory } from 'src/app/models/user-story/user-story';
import { User } from 'src/app/models/user/user';
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
  users: Observable<User[]>;
  userStories: Observable<UserStory[]>;

  userOptions: User[] = [];
  userStoryOptions: UserStory[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private taskService: TaskService,
    private userService: UserService,
    private userStoryService: UserStoryService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: { task: Task; enableable: boolean }
  ) {
    this.users = this.userService.list();
    this.userStories = this.userStoryService.list();

    this.form = this.formBuilder.group({
      id: ['', [Validators.required]],
      title: ['', [Validators.required]],
      assignee: [''],
      reporter: [''],
      assignmentDate: [''],
      endDate: [''],
      storyPoints: [''],
      description: [''],
      status: ['TO_DO'],
      userStory: ['', [Validators.required]],
    });

    if (data) {
      this.form.patchValue({
        id: data.task.id,
        title: data.task.title,
        assignee: data.task.assignee,
        reporter: data.task.reporter,
        assignmentDate: data.task.assignmentDate,
        endDate: data.task.endDate,
        storyPoints: data.task.storyPoints,
        description: data.task.description,
        userStory: data.task.userStory,
      });
    }
  }

  onSubmit() {
    console.log(this.form.value);
    this.taskService.save(this.form.value).subscribe(
      (result) => this.onSuccess(),
      (error) => this.onError()
    );
  }

  onRestore() {
    this.form.patchValue({ status: 'TO_DO' });
  }

  private onSuccess() {
    this.snackBar.open('Tarefa salva com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar tarefa!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  loadUsers() {
    this.userService
      .list()
      .subscribe((options) => (this.userOptions = options));

    this.userStoryService
      .list()
      .subscribe((options) => (this.userStoryOptions = options));
  }

  ngOnInit() {
    this.loadUsers();
  }
}
