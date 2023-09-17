export interface Task {
  id: string;
  title: string;
  user: string;
  assignmentDate: Date;
  endDate: Date;
  effort: number;
  description: string;
  status: string;
}
