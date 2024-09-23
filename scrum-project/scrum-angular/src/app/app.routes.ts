import { Routes } from '@angular/router';
import { authGuard } from './guards/auth/auth.guard';

export const APP_ROUTES: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  {
    path: 'login',
    loadComponent: () =>
      import('./components/login/login.component').then((component) => component.LoginComponent),
  },
  {
    path: 'projects',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./components/project/project.component').then(
        (component) => component.ProjectComponent
      ),
  },
  {
    path: 'board',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./components/board/board.component').then(
        (component) => component.BoardComponent
      ),
  },
  {
    path: 'tasks',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./components/task/task.component').then((component) => component.TaskComponent),
  },
  {
    path: 'sprints',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./components/sprint/sprint.component').then(
        (component) => component.SprintComponent
      ),
  },
  {
    path: 'user-stories',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./components/user-story/user-story.component').then(
        (component) => component.UserStoryComponent
      ),
  },
  {
    path: 'waste-bin',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./components/waste-bin/waste-bin.component').then(
        (component) => component.WasteBinComponent
      ),
  },
];
