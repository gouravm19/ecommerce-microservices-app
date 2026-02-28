import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';

@Component({standalone:true,imports:[ReactiveFormsModule],template:`<h2>Register</h2><form [formGroup]='form' (ngSubmit)='submit()'><input formControlName='fullName' placeholder='Name'><input formControlName='email' placeholder='Email'><input type='password' formControlName='password' placeholder='Password'><button>Create account</button></form>`})
export class RegisterPage{ form=this.fb.group({fullName:'',email:'',password:'',role:'CUSTOMER'}); constructor(private fb:FormBuilder,private api:ApiService){} submit(){ this.api.register(this.form.value).subscribe(); }}
