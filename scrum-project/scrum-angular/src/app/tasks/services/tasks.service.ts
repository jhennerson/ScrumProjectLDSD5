import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs';

import { Task } from '../model/task';

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

  updateTask(task: Task) {
    const url = '/api/tasks/' + task.id;

    const body = {
      status: task.status
    };

    this.httpClient.post(url, body).subscribe({
      next: (v) => console.log(v),
      error: (e) => console.error(e + "Erro ao enviar os dados! STATUS DA TASK = " + task.status),
      complete: () => console.info("Estado da task alterado com sucesso! STATUS DA TASK = " + task.status)
    });
  }
}
