import { Person } from '../person/person';
import { UserStory } from '../user-story/user-story';

export interface Task {
  id: number;
  title: string;
  person: Person;
  assignmentDate: Date;
  endDate: Date;
  storyPoints: number;
  description: string;
  status: string;
  userStory: UserStory;
}
