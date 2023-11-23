import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WasteBinComponent } from 'src/app/components/waste-bin/waste-bin.component';

const routes: Routes = [{ path: '', component: WasteBinComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class WasteBinRoutingModule {}
