import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  form: FormGroup;
  private formSubmitAttempt: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private router: Router,
    public snackBar: MatSnackBar
  ) {
    this.form = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  login() {
    this.formSubmitAttempt = true;

    this.authenticationService
      .login(this.form.value)
      .pipe(tap(() => this.router.navigate(['board'])))
      .subscribe(
        (result) => this.onSuccess(),
        (error) => this.onError()
      );
  }

  private onSuccess() {
    this.snackBar.open('Seja bem vindo!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Dados inválidos!', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  isFieldInvalid(field: string) {
    const control = this.form.get(field);
    return (
      (control && !control.valid && control.touched) ||
      (control && control.untouched && this.formSubmitAttempt)
    );
  }
}
