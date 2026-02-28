import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { AsyncPipe, NgFor } from '@angular/common';
import { OrderStatusBadgeComponent } from '../components/order-status-badge.component';

@Component({standalone:true,imports:[AsyncPipe, NgFor, OrderStatusBadgeComponent],template:`<h2>Orders</h2><div *ngFor='let o of orders'><span>#{{o.id}}</span> <app-order-status-badge [status]='o.status'></app-order-status-badge></div>`})
export class OrdersPage implements OnInit{ orders:any[]=[]; constructor(private api:ApiService){} ngOnInit(){ this.api.orders().subscribe(v=>this.orders=v); }}
