import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BacklogComponent } from 'src/app/components/backlog/backlog.component';
import { BoardComponent } from 'src/app/components/board/board.component';
import { UserStoryComponent } from 'src/app/components/user-story/user-story.component';
import { TaskFormModalComponent } from 'src/app/shared/components/task-form-modal/task-form-modal.component';

const routes: Routes = [
  { path: '', component: BoardComponent },
  { path: 'backlog', component: BacklogComponent },
  { path: 'edit/:id', component: TaskFormModalComponent },
  { path: 'user-story', component: UserStoryComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BoardRoutingModule {}
