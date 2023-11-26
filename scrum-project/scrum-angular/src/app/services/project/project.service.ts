import { first } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Project } from 'src/app/models/project/project';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private readonly API = environment.apiUrl + 'projects';

  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<Project[]>(this.API).pipe(first());
  }

  loadById(id: string) {
    return this.httpClient.get<Project>(`${this.API}/${id}`);
  }

  save(record: Partial<Project>) {
    if (record.id) {
      return this.update(record);
    }

    return this.create(record);
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

  private create(record: Partial<Project>) {
    return this.httpClient.post<Project>(this.API, record).pipe(first());
  }

  private update(record: Partial<Project>) {
    return this.httpClient
      .put<Project>(`${this.API}/${record.id}`, record)
      .pipe(first());
  }
}
