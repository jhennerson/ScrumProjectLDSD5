import { Project } from '../project/project';
import { User } from '../user/user';

export interface UserStory {
  id: string;
  title: string;
  project: Project;
  assignee: User;
  reporter: User;
  description: string;
}
