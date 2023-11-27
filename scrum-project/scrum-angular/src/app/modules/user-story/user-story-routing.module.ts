import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserStoryComponent } from 'src/app/components/user-story/user-story.component';

const routes: Routes = [{ path: '', component: UserStoryComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserStoryRoutingModule {}
