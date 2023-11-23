import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable, first } from 'rxjs';
import { Token } from 'src/app/models/token/token';
import { User } from 'src/app/models/user/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly API = 'api/auth';

  constructor(
    private httpClient: HttpClient,
    private cookieService: CookieService
  ) {}

  signin(record: Partial<User>): Observable<Token> {
    return this.httpClient
      .post<Token>(`${this.API}/login`, record)
      .pipe(first());
  }

  signup(record: Partial<User>): Observable<User> {
    return this.httpClient
      .post<User>(`${this.API}/register`, record)
      .pipe(first());
  }

  isLoggedIn(): boolean {
    const JWT_TOKEN = this.cookieService.get('JWT_TOKEN');
    return !!JWT_TOKEN;
  }
}
