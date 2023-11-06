import { User } from '../user/user';
import { UserStory } from '../user-story/user-story';

export interface Task {
  id: string;
  title: string;
  assignee: User;
  reporter: User;
  assignmentDate: Date;
  endDate: Date;
  storyPoints: number;
  description: string;
  status: string;
  userStory: UserStory;
}
