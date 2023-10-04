import { User } from '../user/user';

export interface UserStory {
  id: string;
  title: string;
  assignee: User;
  reporter: User;
  description: string;
}
