import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first } from 'rxjs';
import { Sprint } from 'src/app/models/sprint/sprint';

@Injectable({
  providedIn: 'root',
})
export class SprintService {
  private readonly API = 'api/sprints';

  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<Sprint[]>(this.API).pipe(first());
  }

  loadById(id: string) {
    return this.httpClient.get<Sprint>(`${this.API}/${id}`);
  }

  save(record: Partial<Sprint>) {
    if (record.id) {
      return this.update(record);
    }

    return this.create(record);
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

  private create(record: Partial<Sprint>) {
    return this.httpClient.post<Sprint>(this.API, record).pipe(first());
  }

  private update(record: Partial<Sprint>) {
    return this.httpClient
      .put<Sprint>(`${this.API}/${record.id}`, record)
      .pipe(first());
  }
}
