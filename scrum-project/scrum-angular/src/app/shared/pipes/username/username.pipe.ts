import { Pipe, PipeTransform } from '@angular/core';
import { Observable, map } from 'rxjs';
import { User } from 'src/app/models/user/user';
import { UserService } from 'src/app/services/user/user.service';

@Pipe({
  name: 'username',
})
export class UsernamePipe implements PipeTransform {
  constructor(private userService: UserService) {}

  transform(userId: string): Observable<string> {
    return this.userService
      .loadById(userId)
      .pipe(map((user: User) => user.username));
  }
}
