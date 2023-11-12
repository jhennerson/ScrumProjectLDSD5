import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatOptionModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ConfirmationDialogComponent } from '../components/confirmation-dialog/confirmation-dialog.component';
import { MenuComponent } from '../components/menu/menu.component';
import { NavbarComponent } from '../components/navbar/navbar.component';
import { TaskFormModalComponent } from '../components/task-form-modal/task-form-modal.component';
import { UserStoryFormModalComponent } from '../components/user-story-form-modal/user-story-form-modal.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import {
  MatDatepickerModule,
  matDatepickerAnimations,
} from '@angular/material/datepicker';

@NgModule({
  declarations: [
    NavbarComponent,
    MenuComponent,
    ConfirmationDialogComponent,
    TaskFormModalComponent,
    UserStoryFormModalComponent,
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatToolbarModule,
    MatDialogModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatDatepickerModule,
  ],
  exports: [
    NavbarComponent,
    MenuComponent,
    ConfirmationDialogComponent,
    TaskFormModalComponent,
    UserStoryFormModalComponent,
  ],
})
export class SharedModule {}
