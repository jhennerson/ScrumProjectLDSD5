import { Component } from '@angular/core';

@Component({
  selector: 'app-home-layout',
  template: `
    <app-menu></app-menu>
    <router-outlet></router-outlet>
  `,
  styles: [],
})
export class HomeLayoutComponent {}
