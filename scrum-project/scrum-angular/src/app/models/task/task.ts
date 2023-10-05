import { UserStory } from '../user-story/user-story';

export interface Task {
  id: string;
  title: string;
  userId: string;
  assignmentDate: Date;
  endDate: Date;
  effort: string;
  description: string;
  status: string;
  userStory: UserStory;
}
