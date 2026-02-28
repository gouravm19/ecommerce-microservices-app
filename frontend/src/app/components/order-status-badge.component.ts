import { Component, Input } from '@angular/core';
@Component({selector:'app-order-status-badge',standalone:true,template:`<span class='badge'>{{status}}</span>`,styles:[`.badge{padding:.2rem .6rem;border-radius:999px;background:#00e5ff22;border:1px solid #00e5ff66}`]})
export class OrderStatusBadgeComponent{ @Input() status='PENDING'; }
