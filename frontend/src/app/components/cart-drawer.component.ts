import { Component } from '@angular/core';

@Component({
  selector: 'app-cart-drawer',
  standalone: true,
  template: `<aside class="drawer">Quick Cart Drawer</aside>`,
  styles:[`.drawer{position:fixed;right:0;top:70px;background:#121a2b;padding:.6rem;border-left:1px solid #00e5ff33;border-bottom:1px solid #00e5ff33;border-radius:0 0 0 8px}`]
})
export class CartDrawerComponent {}
