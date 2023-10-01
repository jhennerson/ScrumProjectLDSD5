import { User } from '../user/user';

export interface Task {
  id: string;
  title: string;
  user: User;
  assignmentDate: Date;
  endDate: Date;
  effort: string;
  description: string;
  status: string;
}
