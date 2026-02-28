import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavBarComponent } from './components/navbar.component';
import { CartDrawerComponent } from './components/cart-drawer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavBarComponent, CartDrawerComponent],
  template: `<app-navbar></app-navbar><app-cart-drawer></app-cart-drawer><main class="container"><router-outlet/></main>`,
  styleUrl: './app.component.scss'
})
export class AppComponent {}
