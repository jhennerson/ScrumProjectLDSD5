import { User } from '../user/user';

export interface UserStory {
  id: number;
  title: string;
  assignee: User;
  reporter: User;
  description: string;
}
