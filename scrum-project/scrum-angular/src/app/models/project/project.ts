import { Sprint } from '../sprint/sprint';
import { UserStory } from '../user-story/user-story';
import { User } from '../user/user';

export interface Project {
  id: string;
  title: string;
  reporter: User;
  assignmentDate: Date;
  endDate: Date;
  members: User[];
  userStories: UserStory[];
  sprints: Sprint[];
}
