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
    this.router.navigate(['board']);
  }

  toTask() {
    this.router.navigate(['tasks']);
  }

  toUserStory() {
    this.router.navigate(['user-stories']);
  }

  toWasteBin() {
    this.router.navigate(['waste-bin']);
  }
}