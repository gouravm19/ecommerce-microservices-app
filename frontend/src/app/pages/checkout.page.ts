import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';

@Component({standalone:true,imports:[ReactiveFormsModule],template:`<h2>Checkout</h2><form [formGroup]='form' (ngSubmit)='submit()'><input formControlName='shippingName' placeholder='Name'><input formControlName='shippingAddress' placeholder='Address'><input formControlName='paymentMethod' placeholder='Payment'><button>Place Order</button></form>`})
export class CheckoutPage{ form=this.fb.group({shippingName:'',shippingAddress:'',paymentMethod:'CARD'}); constructor(private fb:FormBuilder,private api:ApiService){} submit(){ this.api.checkout(this.form.value).subscribe(); }}
