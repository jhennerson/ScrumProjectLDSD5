import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Sprint } from 'src/app/models/sprint/sprint';
import { Task } from 'src/app/models/task/task';
import { TaskService } from 'src/app/services/task/task.service';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

@Component({
  selector: 'app-user-story',
  templateUrl: './user-story.component.html',
  styleUrls: ['./user-story.component.scss'],
})
export class UserStoryComponent implements OnInit {
  tasks: Observable<Task[]>;

  displayedColumns = [
    'actions',
    'title',
    'assignedTo',
    'assignmentDate',
    'endDate',
    'effort',
    'status',
  ];

  constructor(
    private taskService: TaskService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {
    this.tasks = this.taskService.list();
  }

  sprints: Sprint[] = [
    {
      id: '1',
      title: 'Sprint atual',
      assignmentDate: new Date(),
      endDate: new Date(),
      description: 'Sprint atual',
      status: 'Em execução',
    },
  ];

  onAdd() {
    let _modal = this.dialog.open(TaskFormModalComponent, {});

    _modal.afterClosed().subscribe((task) => {
      this.ngOnInit();
    });
  }

  onEdit(task: Task) {
    let _modal = this.dialog.open(TaskFormModalComponent, {
      data: task,
    });

    _modal.afterClosed().subscribe((task) => {
      this.ngOnInit();
    });
  }

  loadTasks() {
    this.tasks = this.taskService.list();
  }

  ngOnInit() {
    this.loadTasks();
  }
}
