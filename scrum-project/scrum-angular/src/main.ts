import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';


import { MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { AuthInterceptor } from './app/interceptors/auth.interceptor';
import { BrowserModule, bootstrapApplication } from '@angular/platform-browser';
import { AppRoutingModule } from './app/app-routing.module';
import { provideAnimations } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { CdkMenuModule } from '@angular/cdk/menu';
import { MatButtonModule } from '@angular/material/button';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatOptionModule } from '@angular/material/core';
import { AppComponent } from './app/app.component';
import { importProvidersFrom } from '@angular/core';
import { APP_ROUTES } from './app/app.routes';
import { provideRouter } from '@angular/router';


bootstrapApplication(AppComponent, {
    providers: [
        importProvidersFrom(BrowserModule, MatToolbarModule, MatCardModule, MatIconModule, DragDropModule, CdkMenuModule, MatButtonModule, ScrollingModule, MatSidenavModule, MatListModule, MatSnackBarModule, MatDialogModule, MatFormFieldModule, MatSelectModule, MatTableModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule, MatDatepickerModule, MatNativeDateModule, FormsModule, MatOptionModule),
        {
            provide: MatDialogRef,
            useValue: {},
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideAnimations(),
        provideRouter(APP_ROUTES)
    ]
})
  .catch(err => console.error(err));
