import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first } from 'rxjs';

import { Task } from '../../models/task/task';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private readonly API = environment.apiUrl +  'tasks';

  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<Task[]>(this.API).pipe(first());
  }

  loadById(id: string) {
    return this.httpClient.get<Task>(`${this.API}/${id}`);
  }

  save(record: Partial<Task>) {
    if (record.id) {
      return this.update(record);
    }

    return this.create(record);
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

  private create(record: Partial<Task>) {
    return this.httpClient.post<Task>(this.API, record).pipe(first());
  }

  private update(record: Partial<Task>) {
    return this.httpClient
      .put<Task>(`${this.API}/${record.id}`, record)
      .pipe(first());
  }
}
