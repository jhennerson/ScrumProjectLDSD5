import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-task-form-modal',
  templateUrl: './task-form-modal.component.html',
  styleUrls: ['./task-form-modal.component.scss'],
})
export class TaskFormModalComponent {
  form = this.formBuilder.group({
    id: [''],
    title: [''],
    assignedTo: [''],
    assignmentDate: [''],
    endDate: [''],
    effort: [''],
    description: [''],
    status: [''],
  });

  constructor(
    private formBuilder: NonNullableFormBuilder,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {}
}
