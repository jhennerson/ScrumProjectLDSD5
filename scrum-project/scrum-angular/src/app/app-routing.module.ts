import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'board' },
  {
    path: 'board',
    loadChildren: () =>
      import('./modules/board/board.module').then(
        (module) => module.BoardModule
      ),
  },
  {
    path: 'task',
    loadChildren: () =>
      import('./modules/task/task.module').then((module) => module.TaskModule),
  },
  {
    path: 'user-story',
    loadChildren: () =>
      import('./modules/user-story/user-story.module').then(
        (module) => module.UserStoryModule
      ),
  },
  {
    path: 'waste-bin',
    loadChildren: () =>
      import('./modules/waste-bin/waste-bin.module').then(
        (module) => module.WasteBinModule
      ),
  },
  {
    path: 'login',
    loadChildren: () =>
      import('./modules/login/login.module').then((m) => m.LoginModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
