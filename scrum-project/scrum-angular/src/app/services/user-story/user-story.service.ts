import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first } from 'rxjs';
import { UserStory } from 'src/app/models/user-story/user-story';

@Injectable({
  providedIn: 'root',
})
export class UserStoryService {
  private readonly API = 'api/user-histories';

  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<UserStory[]>(this.API).pipe(first());
  }

  loadById(id: string) {
    return this.httpClient.get<UserStory>(`${this.API}/${id}`);
  }

  save(record: Partial<UserStory>) {
    if (record.id) {
      return this.update(record);
    }

    return this.create(record);
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

  private create(record: Partial<UserStory>) {
    return this.httpClient.post<UserStory>(this.API, record).pipe(first());
  }

  private update(record: Partial<UserStory>) {
    return this.httpClient
      .patch<UserStory>(`${this.API}/${record.id}`, record)
      .pipe(first());
  }
}
