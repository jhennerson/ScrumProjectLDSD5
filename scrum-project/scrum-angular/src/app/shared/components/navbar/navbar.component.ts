import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent {
  constructor(private router: Router, private cookieService: CookieService) {}

  logout(): void {
    this.cookieService.delete('JWT_TOKEN');
    this.router.navigate(['login']);
  }
}
