import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
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
