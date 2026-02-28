import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { NgFor, NgIf, CurrencyPipe, DatePipe } from '@angular/common';

@Component({
  standalone:true,
  imports:[NgIf, NgFor, CurrencyPipe, DatePipe],
  template:`
    <h2>Order Detail</h2>
    <div *ngIf="order">
      <p><strong>Order:</strong> #{{order.id}}</p>
      <p><strong>Status:</strong> {{order.status}}</p>
      <p><strong>Date:</strong> {{order.createdAt | date:'medium'}}</p>
      <p><strong>Total:</strong> {{order.total | currency}}</p>
      <h3>Items</h3>
      <ul>
        <li *ngFor="let item of order.items">{{item.product?.name}} x {{item.quantity}} — {{item.unitPrice | currency}}</li>
      </ul>
    </div>
  `
})
export class OrderDetailPage implements OnInit{
  order:any;
  constructor(private route:ActivatedRoute, private api:ApiService){}
  ngOnInit(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.api.orderById(id).subscribe(o=>this.order=o);
  }
}
