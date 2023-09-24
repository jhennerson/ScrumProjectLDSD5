import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BacklogComponent } from 'src/app/components/backlog/backlog.component';
import { BoardComponent } from 'src/app/components/board/board.component';

const routes: Routes = [
  { path: 'backlog', component: BacklogComponent },
  { path: 'board', component: BoardComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MenuRoutingModule {}
