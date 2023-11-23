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

  loadById(id: string) {
    return this.httpClient.get<User>(`${this.API}/${id}`);
  }

  save(record: Partial<User>) {
    if (record.id) {
      return this.update(record);
    }

    return this.create(record);
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

  private create(record: Partial<User>) {
    return this.httpClient.post<User>(this.API, record).pipe(first());
  }

  private update(record: Partial<User>) {
    return this.httpClient
      .put<User>(`${this.API}/${record.id}`, record)
      .pipe(first());
  }
}
