import { CookieService } from 'ngx-cookie-service';
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
  signinForm: FormGroup;
  signupForm: FormGroup;
  signinCard = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private cookieService: CookieService,
    private router: Router,
    public snackBar: MatSnackBar
  ) {
    this.signinForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });

    this.signupForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  signin() {
    if (this.signinForm.value && this.signinForm.valid) {
      this.authService.signin(this.signinForm.value).subscribe({
        next: (response) => {
          this.cookieService.set('JWT_TOKEN', response?.token);
          this.signinForm.reset();
          this.signinCard = true;
          this.onLoginSuccess(this.signinForm.value.username);
          this.router.navigate(['/board']);
        },
        error: () => this.onError(),
      });
    }
  }

  signup() {
    if (this.signupForm.value && this.signupForm.valid) {
      this.authService.signup(this.signupForm.value).subscribe({
        next: () => {
          this.signinForm.reset();
          this.signinCard = true;
          this.onSignupSuccess();
          this.router.navigate(['/login']);
        },
        error: () => this.onError(),
      });
    }
  }

  private onLoginSuccess(username: string) {
    this.snackBar.open('Seja bem vindo ', 'X', {
      duration: 2000,
      panelClass: 'task-status-snackbar',
    });
  }

  private onSignupSuccess() {
    this.snackBar.open('Usuário cadastrado com sucesso!', 'X', {
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
