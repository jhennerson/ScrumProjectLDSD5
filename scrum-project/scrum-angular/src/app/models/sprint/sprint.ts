import { User } from '../user/user';

export interface Sprint {
  id: string;
  title: string;
  reporter: User;
  description: string;
  assignmentDate: Date;
  endDate: Date;
}
