import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first, map, mergeMap } from 'rxjs';
import { Project } from 'src/app/models/project/project';
import { UserStory } from 'src/app/models/user-story/user-story';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ProjectService } from 'src/app/services/project/project.service';
import { UserStoryService } from 'src/app/services/user-story/user-story.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { UserStoryFormModalComponent } from 'src/app/shared/components/user-story-form-modal/user-story-form-modal.component';

@Component({
  selector: 'app-user-story',
  templateUrl: './user-story.component.html',
  styleUrls: ['./user-story.component.scss'],
})
export class UserStoryComponent implements OnInit {
  userStories: Observable<UserStory[]> = new Observable<UserStory[]>();
  projects: Observable<Project[]> = new Observable<Project[]>();
  selectedProjectId: string | undefined = undefined;

  displayedColumns = [
    'title',
    'project',
    'assignee',
    'reporter',
    'description',
    'actions',
  ];

  constructor(
    private userStoryService: UserStoryService,
    private authService: AuthService,
    private projectService: ProjectService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {}

  onAdd() {
    const dialogRef = this.dialog.open(UserStoryFormModalComponent, {});

    dialogRef.afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }

  onEdit(userStory: UserStory) {
    const dialogRef = this.dialog.open(UserStoryFormModalComponent, {
      data: userStory,
    });

    dialogRef.afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }

  onDelete(userStory: UserStory) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Deseja apagar essa história de usuário permanentemente?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.userStoryService.remove(userStory.id).subscribe({
          next: () => {
            this.onHardDelete();
            this.ngOnInit();
          },
          error: () => this.onError(),
        });
      }
    });
  }

  private onHardDelete() {
    this.snackBar.open('Hisória de usuário apagada com sucesso!', 'X', {
      duration: 2000,
      panelClass: 'success-snackbar',
    });
  }

  private onError() {
    this.snackBar.open('Erro ao apagar história de usuário!', 'X', {
      duration: 3000,
      panelClass: 'error-snackbar',
    });
  }

  loadUserStories(projectId: string) {
    if (projectId) {
      this.authService.getCurrentUser().subscribe((user) => {
        const currentUserId = user.id;

        this.userStories = this.userStoryService
          .listByProjectId(projectId)
          .pipe(
            map((userStory) =>
              userStory.filter(
                (userStory) =>
                  (userStory.reporter &&
                    userStory.reporter.id === currentUserId) ||
                  (userStory.assignee &&
                    userStory.assignee.id === currentUserId)
              )
            )
          );
      });
    }
  }

  loadProjects() {
    this.projects = this.authService.getCurrentUser().pipe(
      mergeMap((user) => {
        const memberProjects = user.memberProjects || [];
        const reporterProjects = user.reporterProjects || [];

        const distinctProjects = [
          ...memberProjects,
          ...reporterProjects.filter(
            (reporterProject) =>
              !memberProjects.some(
                (memberProject) => memberProject.id === reporterProject.id
              )
          ),
        ];

        return this.projectService.list().pipe(
          first(),
          map((allProjects) =>
            allProjects.filter((project) =>
              distinctProjects.some(
                (distinctProject) => distinctProject.id === project.id
              )
            )
          )
        );
      })
    );
  }

  onProjectChange() {
    if (this.selectedProjectId) {
      this.loadUserStories(this.selectedProjectId);
    }
  }

  ngOnInit() {
    this.loadProjects();

    if (this.selectedProjectId) {
      this.loadUserStories(this.selectedProjectId);
    }
  }
}
