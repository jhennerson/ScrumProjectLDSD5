import { DragDropModule } from '@angular/cdk/drag-drop';
import { CdkMenuModule } from '@angular/cdk/menu';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BacklogComponent } from './components/backlog/backlog.component';
import { BoardComponent } from './components/board/board.component';
import { MenuComponent } from './components/menu/menu.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SprintComponent } from './components/sprint/sprint.component';
import { UserStoryComponent } from './components/user-story/user-story.component';
import { TaskFormModalComponent } from './shared/components/task-form-modal/task-form-modal.component';
import { UserStoryFormModalComponent } from './shared/components/user-story-form-modal/user-story-form-modal.component';
import { StatusPipe } from './shared/pipes/status/status.pipe';
import { UsernamePipe } from './shared/pipes/username/username.pipe';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    MenuComponent,
    BoardComponent,
    SprintComponent,
    BacklogComponent,
    StatusPipe,
    TaskFormModalComponent,
    UsernamePipe,
    UserStoryComponent,
    UserStoryFormModalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    DragDropModule,
    CdkMenuModule,
    MatCardModule,
    MatButtonModule,
    ScrollingModule,
    MatSidenavModule,
    MatListModule,
    HttpClientModule,
    MatSnackBarModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSelectModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
  ],
  providers: [
    {
      provide: MatDialogRef,
      useValue: {},
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
