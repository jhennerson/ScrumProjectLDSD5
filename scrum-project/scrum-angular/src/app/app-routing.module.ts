import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'board' },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'board',
    loadChildren: () =>
      import('./modules/board/board.module').then((m) => m.BoardModule),
  },
  {
    path: 'backlog',
    loadChildren: () =>
      import('./modules/backlog/backlog.module').then((m) => m.BacklogModule),
  },
  {
    path: 'user-story',
    loadChildren: () =>
      import('./modules/user-story/user-story.module').then(
        (m) => m.UserStoryModule
      ),
  },
  {
    path: 'waste-bin',
    loadChildren: () =>
      import('./modules/waste-bin/waste-bin.module').then(
        (m) => m.WasteBinModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
