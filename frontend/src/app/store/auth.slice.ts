import { createReducer, on, createAction, props } from '@ngrx/store';

export interface AuthState { user:any|null; token:string|null; isLoggedIn:boolean; }
const initial: AuthState = { user:null, token:localStorage.getItem('token'), isLoggedIn:!!localStorage.getItem('token') };
export const loginSuccess = createAction('[Auth] Login Success', props<{token:string,user:any}>());
export const logout = createAction('[Auth] Logout');

export const authReducer = createReducer(initial,
  on(loginSuccess, (s,a)=>{ localStorage.setItem('token', a.token); return {...s, token:a.token, user:a.user, isLoggedIn:true}; }),
  on(logout, (s)=>{ localStorage.removeItem('token'); return {...s, token:null, user:null, isLoggedIn:false}; })
);
