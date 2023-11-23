import { Pipe, PipeTransform } from '@angular/core';
import { map, Observable } from 'rxjs';
import { User } from 'src/app/models/user/user';
import { UserService } from 'src/app/services/user/user.service';

@Pipe({
  name: 'username',
})
export class UsernamePipe implements PipeTransform {
  constructor(private userService: UserService) {}

  transform(id: string): Observable<string> {
    return this.userService
      .loadById(id)
      .pipe(map((user: User) => user.username));
  }
}
