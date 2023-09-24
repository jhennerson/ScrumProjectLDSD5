import { Location } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Task } from 'src/app/models/task/task';
import { TaskService } from 'src/app/services/task/task.service';

@Component({
  selector: 'app-task-form-modal',
  templateUrl: './task-form-modal.component.html',
  styleUrls: ['./task-form-modal.component.scss'],
})
export class TaskFormModalComponent {
  taskForm: FormGroup;
  selectedUserName = 'user1';

  constructor(
    private formBuilder: FormBuilder,
    private service: TaskService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute,
    @Inject(MAT_DIALOG_DATA) public data: Task
  ) {
    this.taskForm = this.formBuilder.group({
      id: ['', [Validators.required]],
      title: ['', [Validators.required]],
      user: [''],
      assignmentDate: [''],
      endDate: [''],
      effort: [''],
      description: [''],
      status: ['TO_DO'],
    });

    if (data) {
      this.taskForm.patchValue({
        title: data.title,
        user: data.user.username,
        assignmentDate: data.assignmentDate,
        endDate: data.endDate,
        effort: data.effort,
        description: data.description,
      });
    }
  }

  onSubmit() {
    console.log(this.taskForm.value);
  }
}
