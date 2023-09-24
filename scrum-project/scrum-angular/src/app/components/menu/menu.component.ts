import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
})
export class MenuComponent {
  constructor(private router: Router, private route: ActivatedRoute) {}

  toBoard() {
    this.router.navigate(['board'], { relativeTo: this.route });
  }

  toBacklog() {
    this.router.navigate(['backlog'], { relativeTo: this.route });
  }
}
