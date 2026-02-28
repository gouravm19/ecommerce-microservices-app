import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Store } from '@ngrx/store';

export const authGuard: CanActivateFn = (route) => {
  const store = inject(Store<any>); const router = inject(Router);
  let allowed = false; let state:any;
  store.select(s=>s.auth).subscribe(v=>state=v).unsubscribe();
  allowed = !!state?.token;
  if (allowed && route.data?.['role']) { allowed = state.user?.role === route.data['role']; }
  if (!allowed) router.navigateByUrl('/auth/login');
  return allowed;
};
