import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { UserStory } from 'src/app/models/user-story/user-story';
import { Person } from 'src/app/models/person/person';
import { UserStoryService } from 'src/app/services/user-story/user-story.service';
import { PersonService } from 'src/app/services/person/person.service';

@Component({
  selector: 'app-user-story-modal',
  templateUrl: './user-story-form-modal.component.html',
  styleUrls: ['./user-story-form-modal.component.scss'],
})
export class UserStoryFormModalComponent implements OnInit {
  form: FormGroup;
  users: Observable<Person[]>;

  userOptions: Person[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private userStoryService: UserStoryService,
    private personService: PersonService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: UserStory
  ) {
    this.users = this.personService.list();

    this.form = this.formBuilder.group({
      id: ['', [Validators.required]],
      title: ['', Validators.required],
      assignee: [''],
      reporter: [''],
      description: [''],
    });

    if (data) {
      this.form.patchValue({
        id: data.id,
        title: data.title,
        assignee: data.assignee,
        reporter: data.reporter,
        description: data.description,
      });
    }
  }

  onSubmit() {
    this.userStoryService.save(this.form?.value).subscribe(
      (result) => this.onSuccess(),
      (error) => this.onError()
    );
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
    this.personService
      .list()
      .subscribe((options) => (this.userOptions = options));
  }

  ngOnInit() {
    this.loadUsers();
  }
}
