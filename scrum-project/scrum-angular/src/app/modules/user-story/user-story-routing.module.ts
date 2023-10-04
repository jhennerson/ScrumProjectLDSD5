import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BacklogComponent } from 'src/app/components/backlog/backlog.component';
import { BoardComponent } from 'src/app/components/board/board.component';
import { UserStoryComponent } from 'src/app/components/user-story/user-story.component';

const routes: Routes = [
  { path: '', component: UserStoryComponent },
  { path: 'board', component: BoardComponent },
  { path: 'backlog', component: BacklogComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserStoryRoutingModule {}
