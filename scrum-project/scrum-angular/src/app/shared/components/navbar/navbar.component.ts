import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { MatToolbar } from '@angular/material/toolbar';
import { MatButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss'],
    standalone: true,
    imports: [
        MatToolbar,
        MatButton,
        MatIcon,
    ],
})
export class NavbarComponent {
  constructor(private router: Router, private cookieService: CookieService) {}

  logout(): void {
    this.cookieService.delete('JWT_TOKEN');
    this.router.navigate(['login']);
  }
}
