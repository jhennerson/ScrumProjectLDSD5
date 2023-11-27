import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatOptionModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ConfirmationDialogComponent } from '../components/confirmation-dialog/confirmation-dialog.component';
import { MenuComponent } from '../components/menu/menu.component';
import { NavbarComponent } from '../components/navbar/navbar.component';
import { SprintFormModalComponent } from '../components/sprint-form-modal/sprint-form-modal.component';
import { TaskFormModalComponent } from '../components/task-form-modal/task-form-modal.component';
import { UserStoryFormModalComponent } from '../components/user-story-form-modal/user-story-form-modal.component';
import { ProjectFormModalComponent } from '../components/project-form-modal/project-form-modal.component';

@NgModule({
  declarations: [
    NavbarComponent,
    MenuComponent,
    ConfirmationDialogComponent,
    TaskFormModalComponent,
    UserStoryFormModalComponent,
    SprintFormModalComponent,
    ProjectFormModalComponent,
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatToolbarModule,
    MatDialogModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatIconModule,
  ],
  exports: [
    NavbarComponent,
    MenuComponent,
    ConfirmationDialogComponent,
    TaskFormModalComponent,
    UserStoryFormModalComponent,
    SprintFormModalComponent,
    ProjectFormModalComponent,
  ],
})
export class SharedModule {}
