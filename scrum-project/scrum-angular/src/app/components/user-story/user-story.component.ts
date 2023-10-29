import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first } from 'rxjs';
import { Sprint } from 'src/app/models/sprint/sprint';
import { UserStory } from 'src/app/models/user-story/user-story';
import { UserStoryService } from 'src/app/services/user-story/user-story.service';
import { UserStoryFormModalComponent } from 'src/app/shared/components/user-story-form-modal/user-story-form-modal.component';

@Component({
  selector: 'app-user-story',
  templateUrl: './user-story.component.html',
  styleUrls: ['./user-story.component.scss'],
})
export class UserStoryComponent implements OnInit {
  userStories: Observable<UserStory[]>;

  displayedColumns = [
    'title',
    'assignee',
    'reporter',
    'description',
    'actions',
  ];

  constructor(
    private userStoryService: UserStoryService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {
    this.userStories = this.userStoryService.list().pipe(first());
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
    const dialogRef = this.dialog.open(UserStoryFormModalComponent, {});

    dialogRef.afterClosed().subscribe(() => {
      this.loadTasks();
    });
  }

  onEdit(userStory: UserStory) {
    const dialogRef = this.dialog.open(UserStoryFormModalComponent, {
      data: userStory,
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadTasks();
    });
  }

  loadTasks() {
    this.userStories = this.userStoryService.list().pipe(first());
  }

  ngOnInit() {
    this.loadTasks();
  }
}
