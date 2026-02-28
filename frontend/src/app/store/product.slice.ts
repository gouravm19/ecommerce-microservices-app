import { createAction, createReducer, on, props } from '@ngrx/store';

export interface ProductState { products:any[]; loading:boolean; filters:any; }
const initial: ProductState = { products:[], loading:false, filters:{} };
export const setProducts = createAction('[Product] Set', props<{products:any[]}>());
export const setLoading = createAction('[Product] Loading', props<{loading:boolean}>());
export const setFilters = createAction('[Product] Filters', props<{filters:any}>());
export const productReducer = createReducer(initial,
  on(setProducts, (s,a)=>({...s, products:a.products})),
  on(setLoading, (s,a)=>({...s, loading:a.loading})),
  on(setFilters, (s,a)=>({...s, filters:a.filters}))
);
