import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TaskComponent } from 'src/app/components/task/task.component';
import { BoardComponent } from 'src/app/components/board/board.component';
import { UserStoryComponent } from 'src/app/components/user-story/user-story.component';
import { WasteBinComponent } from 'src/app/components/waste-bin/waste-bin.component';

const routes: Routes = [
  { path: '', component: WasteBinComponent },
  { path: 'board', component: BoardComponent },
  { path: 'task', component: TaskComponent },
  { path: 'user-story', component: UserStoryComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class WasteBinRoutingModule {}
