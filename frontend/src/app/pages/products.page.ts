import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { ProductCardComponent } from '../components/product-card.component';
import { Store } from '@ngrx/store';
import { setProducts } from '../store/product.slice';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { setCart } from '../store/cart.slice';

@Component({
  standalone:true,
  imports:[ProductCardComponent, AsyncPipe, NgFor, NgIf],
  template:`
    <h2>Products</h2>
    <p *ngIf="error" class="error">{{error}}</p>
    <div class='grid'>
      <app-product-card
        *ngFor='let p of (products$|async)'
        [product]='p'
        (add)='addToCart($event)'>
      </app-product-card>
    </div>`,
  styles:[`.grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(220px,1fr));gap:1rem}.error{color:#ff6d00}`]
})
export class ProductsPage implements OnInit{
  products$ = this.store.select((s:any)=>s.product.products);
  error = '';
  constructor(private api:ApiService, private store:Store, private router: Router){}

  ngOnInit(){
    this.api.products().subscribe({
      next: (r)=> this.store.dispatch(setProducts({products:r.content || []})),
      error: ()=> this.error = 'Unable to load products. Please check backend at port 8080.'
    });
  }

  addToCart(productId:number){
    const token = localStorage.getItem('token');
    if(!token){
      this.router.navigateByUrl('/auth/login');
      return;
    }
    this.api.addToCart(productId, 1).subscribe(c => this.store.dispatch(setCart(c)));
  }
}
