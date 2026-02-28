import { createReducer, on, createAction, props } from '@ngrx/store';

export interface CartState { items:any[]; total:number; itemCount:number; }
const initial: CartState = { items:[], total:0, itemCount:0 };
export const setCart = createAction('[Cart] Set', props<{items:any[], total:number, itemCount:number}>());
export const cartReducer = createReducer(initial, on(setCart, (_,a)=>({...a})));
