import { Routes } from '@angular/router';
import { authGuard } from './guards/auth/auth.guard';

export const APP_ROUTES: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  {
    path: 'login',
    loadChildren: () =>
      import('./modules/login/login.routes').then((route) => route.LOGIN_ROUTES),
  },
  {
    path: 'projects',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/project/project.routes').then(
        (route) => route.PROJECT_ROUTES
      ),
  },
  {
    path: 'board',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/board/board.routes').then(
        (route) => route.BOARD_ROUTES
      ),
  },
  {
    path: 'tasks',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/task/task.routes').then((route) => route.TASK_ROUTES),
  },
  {
    path: 'sprints',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/sprint/sprint.routes').then(
        (route) => route.SPRINT_ROUTES
      ),
  },
  {
    path: 'user-stories',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/user-story/user-story.routes').then(
        (route) => route.USER_STORY_ROUTES
      ),
  },
  {
    path: 'waste-bin',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/waste-bin/waste-bin.routes').then(
        (route) => route.WASTE_BIN_ROUTES
      ),
  },
];
