import { Pipe, PipeTransform } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Person } from 'src/app/models/person/person';
import { PersonService } from 'src/app/services/person/person.service';

@Pipe({
  name: 'username',
})
export class UsernamePipe implements PipeTransform {
  constructor(private personService: PersonService) {}

  transform(id: string): Observable<string> {
    return this.personService
      .loadById(id)
      .pipe(map((person: Person) => person.username));
  }
}
