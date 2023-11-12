import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TaskComponent } from 'src/app/components/task/task.component';
import { BoardComponent } from 'src/app/components/board/board.component';
import { UserStoryComponent } from 'src/app/components/user-story/user-story.component';

const routes: Routes = [
  { path: '', component: UserStoryComponent },
  { path: 'board', component: BoardComponent },
  { path: 'task', component: TaskComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserStoryRoutingModule {}
