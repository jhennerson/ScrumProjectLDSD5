import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first } from 'rxjs';
import { User } from 'src/app/models/user/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly API = 'api/users';

  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<User[]>(this.API).pipe(first());
  }

  loadById(id: number) {
    return this.httpClient.get<User>(`${this.API}/${id}`);
  }
}
