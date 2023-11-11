import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first } from 'rxjs';
import { Person } from 'src/app/models/person/person';

@Injectable({
  providedIn: 'root',
})
export class PersonService {
  private readonly API = 'api/persons';

  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<Person[]>(this.API).pipe(first());
  }

  loadById(id: string) {
    return this.httpClient.get<Person>(`${this.API}/${id}`);
  }
}
