import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { Credentials, User } from 'src/app/models/user/user';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private readonly API = 'api/auth';

  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(
    false
  );

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(private httpClient: HttpClient) {}

  login(credentials: Credentials): Observable<User> {
    return this.httpClient
      .post(`${this.API}/login`, credentials, {
        observe: 'response',
      })
      .pipe(
        map((response: HttpResponse<any>) => {
          const body = response.body;
          const headers = response.headers;

          const bearerToken = headers.get('Authorization');
          if (bearerToken) {
            const token = bearerToken.replace('Bearer ', '');

            localStorage.setItem('token', token);
          }

          return body;
        })
      );
  }

  getToken() {
    return localStorage.getItem('token');
  }
}
