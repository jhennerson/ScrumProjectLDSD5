import { Project } from '../project/project';
import { User } from '../user/user';

export interface Sprint {
  id: string;
  title: string;
  project: Project;
  reporter: User;
  description: string;
  assignmentDate: Date;
  endDate: Date;
}
