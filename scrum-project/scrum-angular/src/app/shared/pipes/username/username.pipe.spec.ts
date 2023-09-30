import { UsernamePipe } from './username.pipe';
import { User } from 'src/app/models/user/user';
import { UserService } from 'src/app/services/user/user.service';
import { of } from 'rxjs';

describe('UsernamePipe', () => {
  let pipe: UsernamePipe;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(() => {
    userService = jasmine.createSpyObj('UserService', ['loadById']);
    pipe = new UsernamePipe(userService);
  });

  it('should create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('should transform userId into username', () => {
    const userId = 1;
    const user: User = {
      id: '1',
      username: 'testuser',
      email: 'testuser@mail.com',
    };

    userService.loadById.and.returnValue(of(user));

    pipe.transform(userId).subscribe((result) => {
      expect(result).toBe('testuser');
    });

    expect(userService.loadById).toHaveBeenCalledWith(userId);
  });
});
