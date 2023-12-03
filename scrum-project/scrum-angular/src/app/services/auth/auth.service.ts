import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable, first } from 'rxjs';
import { Token } from 'src/app/models/token/token';
import { User } from 'src/app/models/user/user';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly API = environment.apiUrl + 'auth';

  constructor(
    private httpClient: HttpClient,
    private cookieService: CookieService
  ) {}

  login(record: Partial<User>): Observable<Token> {
    return this.httpClient
      .post<Token>(`${this.API}/login`, record)
      .pipe(first());
  }

  register(record: Partial<User>): Observable<User> {
    return this.httpClient
      .post<User>(`${this.API}/register`, record)
      .pipe(first());
  }

  isLoggedIn(): boolean {
    const JWT_TOKEN = this.cookieService.get('JWT_TOKEN');
    return !!JWT_TOKEN;
  }

  getCurrentUser(): Observable<User> {
    return this.httpClient.get<User>(`${this.API}/current-user`).pipe(first());
  }
}
