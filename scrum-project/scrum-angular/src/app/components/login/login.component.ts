import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    public snackBar: MatSnackBar
  ) {
    this.form = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  login() {
    this.authService.login(this.form.value).subscribe(
      (response) => {
        this.onSuccess();
        this.router.navigate(['/board']);
      },
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
}
