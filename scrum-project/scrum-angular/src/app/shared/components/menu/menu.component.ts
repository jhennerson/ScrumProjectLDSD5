import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCard } from '@angular/material/card';
import { MatButton } from '@angular/material/button';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.scss'],
    standalone: true,
    imports: [MatCard, MatButton],
})
export class MenuComponent {
  constructor(private router: Router, private route: ActivatedRoute) {}

  toProjects() {
    this.router.navigate(['projects']);
  }

  toBoard() {
    this.router.navigate(['board']);
  }

  toTasks() {
    this.router.navigate(['tasks']);
  }

  toSprints() {
    this.router.navigate(['sprints']);
  }

  toUserStories() {
    this.router.navigate(['user-stories']);
  }

  toWasteBin() {
    this.router.navigate(['waste-bin']);
  }
}
