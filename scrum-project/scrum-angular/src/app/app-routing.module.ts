import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from './guards/auth/auth.guard';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  {
    path: 'login',
    loadChildren: () =>
      import('./modules/login/login.module').then((m) => m.LoginModule),
  },
  {
    path: 'board',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/board/board.module').then(
        (module) => module.BoardModule
      ),
  },
  {
    path: 'tasks',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/task/task.module').then((module) => module.TaskModule),
  },
  {
    path: 'user-stories',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/user-story/user-story.module').then(
        (module) => module.UserStoryModule
      ),
  },
  {
    path: 'waste-bin',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./modules/waste-bin/waste-bin.module').then(
        (module) => module.WasteBinModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
