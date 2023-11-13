import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, first, map } from 'rxjs';
import { User } from 'src/app/models/user/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly API = 'api/auth';

  constructor(private httpClient: HttpClient) {}

  signin(record: Partial<User>): Observable<User> {
    return this.httpClient
      .post(
        `${this.API}/login`,
        record,
        {
          observe: 'response',
        }
      )
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

  signup(record: Partial<User>): Observable<User> {
    return this.httpClient.post<User>(`${this.API}/register`, record).pipe(first());
  }
}
