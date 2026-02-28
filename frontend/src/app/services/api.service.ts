import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface Product { id:number; name:string; category:string; price:number; description:string; }

@Injectable({providedIn:'root'})
export class ApiService {
  private base='http://localhost:8080/api';
  constructor(private http:HttpClient){}
  products(){ return this.http.get<any>(`${this.base}/products?page=0&size=50`); }
  productById(id:number){ return this.http.get<any>(`${this.base}/products/${id}`); }
  login(payload:any){ return this.http.post<any>(`${this.base}/auth/login`, payload); }
  register(payload:any){ return this.http.post<any>(`${this.base}/auth/register`, payload); }
  cart(){ return this.http.get<any>(`${this.base}/cart`); }
  addToCart(productId:number, quantity:number){ return this.http.post<any>(`${this.base}/cart/add`, {productId, quantity}); }
  checkout(payload:any){ return this.http.post<any>(`${this.base}/orders/checkout`, payload); }
  orders(){ return this.http.get<any>(`${this.base}/orders`); }
  orderById(id:number){ return this.http.get<any>(`${this.base}/orders/${id}`); }
  dashboard(){ return this.http.get<any>(`${this.base}/dashboard/stats`); }
}
