import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { ApiService } from '../services/api.service';
import { setCart } from '../store/cart.slice';
import { AsyncPipe, JsonPipe } from '@angular/common';

@Component({standalone:true,imports:[AsyncPipe, JsonPipe],template:`<h2>Cart</h2><pre>{{(cart$|async)|json}}</pre>`})
export class CartPage implements OnInit{
  cart$=this.store.select((s:any)=>s.cart);
  constructor(private api:ApiService, private store:Store){}
  ngOnInit(){ this.api.cart().subscribe(c=>this.store.dispatch(setCart(c))); }
}
