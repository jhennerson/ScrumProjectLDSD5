import { Project } from '../project/project';
import { Sprint } from '../sprint/sprint';
import { Task } from '../task/task';
import { UserStory } from '../user-story/user-story';

export interface User {
  id: string;
  username: string;
  email: string;
  memberProjects: Project[];
  reporterProjects: Project[];
  reporterSprints: Sprint[];
  assigneeUserStories: UserStory[];
  reporterUserStories: UserStory[];
  assigneeTasks: Task[];
  reporterTasks: Task[];
}
