import { SprintService } from './../../../services/sprint/sprint.service';
import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Sprint } from 'src/app/models/sprint/sprint';
import { User } from 'src/app/models/user/user';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-sprint-form-modal',
  templateUrl: './sprint-form-modal.component.html',
  styleUrls: ['./sprint-form-modal.component.scss'],
})
export class SprintFormModalComponent implements OnInit {
  form: FormGroup;
  users: Observable<User[]> = new Observable<User[]>();

  userOptions: User[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private sprintService: SprintService,
    private userService: UserService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: Sprint
  ) {
    this.form = this.formBuilder.group({
      id: ['', [Validators.required]],
      title: ['', [Validators.required]],
      reporter: [''],
      description: [''],
      assignmentDate: [''],
      endDate: [''],
    });

    if (data) {
      this.form.patchValue({
        id: data.id,
        title: data.title,
        reporter: data.reporter,
        description: data.description,
        assignmentDate: data.assignmentDate,
        endDate: data.endDate,
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
      panelClass: 'sprint-status-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao salvar sprint!', 'X', {
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
