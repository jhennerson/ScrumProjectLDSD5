import { Person } from '../person/person';

export interface UserStory {
  id: string;
  title: string;
  assignee: Person;
  reporter: Person;
  description: string;
}
