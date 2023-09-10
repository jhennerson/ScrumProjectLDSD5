import { Injectable } from '@angular/core';
import { Task } from '../model/task';
import { HttpClient } from '@angular/common/http';
import { first, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  private readonly API = '/assets/tasks.json';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Task[]>(this.API)
    .pipe(
      first(),
      tap(tasks => console.log(tasks))
    );
  }
}
