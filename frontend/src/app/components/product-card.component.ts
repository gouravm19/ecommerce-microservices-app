import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../services/api.service';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports:[CurrencyPipe],
  template:`
  <div class='card'>
    <h3>{{product.name}}</h3>
    <p>{{product.category}}</p>
    <p>{{product.price | currency}}</p>
    <button (click)="add.emit(product.id)">Add to Cart</button>
  </div>`,
  styles:[`.card{border:1px solid #ffffff22;border-radius:14px;padding:1rem;background:#11192a;transition:.25s}.card:hover{transform:translateY(-4px)}button{background:#ff6d00;color:#fff;border:none;border-radius:999px;padding:.45rem .8rem}`]
})
export class ProductCardComponent {
  @Input({required:true}) product!: Product;
  @Output() add = new EventEmitter<number>();
}
