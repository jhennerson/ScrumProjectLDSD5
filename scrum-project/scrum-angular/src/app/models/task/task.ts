// import { User } from '../user/user';

export interface Task {
  id: number;
  title: string;
  userId: number;
  assignmentDate: Date;
  endDate: Date;
  effort: number;
  description: string;
  status: string;
}
