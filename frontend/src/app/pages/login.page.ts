import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';
import { Store } from '@ngrx/store';
import { loginSuccess } from '../store/auth.slice';
import { Router } from '@angular/router';

@Component({standalone:true,imports:[ReactiveFormsModule],template:`<h2>Login</h2><form [formGroup]='form' (ngSubmit)='submit()'><input formControlName='email' placeholder='Email'><input type='password' formControlName='password' placeholder='Password'><button>Login</button></form>`})
export class LoginPage{ form=this.fb.group({email:'customer@test.com',password:'Test@123'}); constructor(private fb:FormBuilder,private api:ApiService,private store:Store,private router:Router){} submit(){ this.api.login(this.form.value).subscribe(r=>{this.store.dispatch(loginSuccess({token:r.token,user:r})); this.router.navigateByUrl('/home');}); }}
