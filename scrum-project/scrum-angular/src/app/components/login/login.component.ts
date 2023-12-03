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
  loginForm: FormGroup;
  signupForm: FormGroup;
  loginCard = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private cookieService: CookieService,
    private router: Router,
    public snackBar: MatSnackBar
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });

    this.signupForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  login() {
    if (this.loginForm.value && this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          let currentDate = new Date();
          let expirationDate = new Date(currentDate.getTime() + 60 * 60000);
          this.cookieService.set('JWT_TOKEN', response?.token, expirationDate);
          this.loginForm.reset();
          this.loginCard = true;
          this.onLoginSuccess(this.loginForm.value.username);
          this.router.navigate(['/board']);
        },
        error: () => this.onError(),
      });
    }
  }

  register() {
    if (this.signupForm.value && this.signupForm.valid) {
      this.authService.register(this.signupForm.value).subscribe({
        next: () => {
          this.loginForm.reset();
          this.loginCard = true;
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
      panelClass: 'success-snackbar',
    });
  }

  private onSignupSuccess() {
    this.snackBar.open('Usuário cadastrado com sucesso!', 'X', {
      duration: 3000,
      panelClass: 'success-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Dados inválidos!', 'X', {
      duration: 3000,
      panelClass: 'error-snackbar',
    });
  }
}
