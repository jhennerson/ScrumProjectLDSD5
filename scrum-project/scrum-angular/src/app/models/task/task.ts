import { User } from '../user/user';
import { UserStory } from '../user-story/user-story';
import { Sprint } from '../sprint/sprint';

export interface Task {
  id: string;
  title: string;
  sprint: Sprint;
  assignee: User;
  reporter: User;
  assignmentDate: Date;
  endDate: Date;
  storyPoints: number;
  description: string;
  status: string;
  userStory: UserStory;
}
