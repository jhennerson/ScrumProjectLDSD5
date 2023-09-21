import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { TaskService } from 'src/app/services/task/task.service';

@Component({
  selector: 'app-task-form-modal',
  templateUrl: './task-form-modal.component.html',
  styleUrls: ['./task-form-modal.component.scss'],
})
export class TaskFormModalComponent {
  form = this.formBuilder.group({
    id: ['', [Validators.required]],
    title: [''],
    user: [''],
    assignmentDate: [''],
    endDate: [''],
    effort: [''],
    description: [''],
    status: ['TO_DO'],
  });

  constructor(
    private formBuilder: FormBuilder,
    private service: TaskService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {}

  onSubmit() {
    console.log(this.form.value);
  }
}
