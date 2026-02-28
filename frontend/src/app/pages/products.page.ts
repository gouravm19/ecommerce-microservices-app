import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { ProductCardComponent } from '../components/product-card.component';
import { Store } from '@ngrx/store';
import { setProducts } from '../store/product.slice';
import { AsyncPipe, NgFor } from '@angular/common';

@Component({standalone:true,imports:[ProductCardComponent, AsyncPipe, NgFor],template:`<h2>Products</h2><div class='grid'><app-product-card *ngFor='let p of (products$|async)' [product]='p'></app-product-card></div>`,styles:[`.grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(220px,1fr));gap:1rem}`]})
export class ProductsPage implements OnInit{
  products$ = this.store.select((s:any)=>s.product.products);
  constructor(private api:ApiService, private store:Store){}
  ngOnInit(){ this.api.products().subscribe(r=>this.store.dispatch(setProducts({products:r.content || []}))); }
}
