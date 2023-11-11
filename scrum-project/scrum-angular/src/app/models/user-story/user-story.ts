import { Person } from '../person/person';

export interface UserStory {
  id: number;
  title: string;
  assignee: Person;
  reporter: Person;
  description: string;
}
