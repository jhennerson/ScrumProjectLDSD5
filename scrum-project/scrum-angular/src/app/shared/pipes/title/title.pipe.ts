import { Pipe, PipeTransform } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Sprint } from 'src/app/models/sprint/sprint';
import { SprintService } from 'src/app/services/sprint/sprint.service';

@Pipe({
  name: 'title',
})
export class TitlePipe implements PipeTransform {
  constructor(private sprintService: SprintService) {}

  transform(id: string): Observable<string> {
    return this.sprintService
      .loadById(id)
      .pipe(map((sprint: Sprint) => sprint.title));
  }
}
