import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Store } from '@ngrx/store';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, AsyncPipe],
  template: `<nav><a routerLink="/home">E-Commerce</a><div class="links"><a routerLink="/products">Products</a><a routerLink="/orders">Orders</a><a routerLink="/admin">Admin</a><a routerLink="/cart">Cart ({{(count$|async) || 0}})</a></div></nav>`,
  styles:[`nav{position:fixed;top:0;left:0;right:0;background:#0a0f1a;border-bottom:1px solid #00e5ff40;padding:1rem;display:flex;justify-content:space-between;z-index:99}.links{display:flex;gap:1rem}a{color:#fff;text-decoration:none}`]
})
export class NavBarComponent { count$ = this.store.select((s:any)=>s.cart.itemCount); constructor(private store:Store){} }
