import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { NgFor, CurrencyPipe } from '@angular/common';
import { OrderStatusBadgeComponent } from '../components/order-status-badge.component';
import { RouterLink } from '@angular/router';

@Component({
  standalone:true,
  imports:[NgFor, CurrencyPipe, OrderStatusBadgeComponent, RouterLink],
  template:`<h2>Orders</h2>
  <div *ngFor='let o of orders' class="row">
    <a [routerLink]="['/orders', o.id]">Order #{{o.id}}</a>
    <app-order-status-badge [status]='o.status'></app-order-status-badge>
    <span>{{o.total | currency}}</span>
  </div>`,
  styles:[`.row{display:flex;gap:1rem;padding:.5rem 0;align-items:center}`]
})
export class OrdersPage implements OnInit{
  orders:any[]=[];
  constructor(private api:ApiService){}
  ngOnInit(){ this.api.orders().subscribe(v=>this.orders=v); }
}
