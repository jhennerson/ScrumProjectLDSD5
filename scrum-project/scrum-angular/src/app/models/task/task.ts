export interface Task {
  id: string;
  title: string;
  assignedTo: string;
  assignmentDate: Date;
  endDate: Date;
  effort: number;
  description: string;
  status: string;
}
