import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', loadComponent: () => import('./pages/home.page').then(m => m.HomePage) },
  { path: 'products', loadComponent: () => import('./pages/products.page').then(m => m.ProductsPage) },
  { path: 'products/:id', loadComponent: () => import('./pages/product-detail.page').then(m => m.ProductDetailPage) },
  { path: 'cart', loadComponent: () => import('./pages/cart.page').then(m => m.CartPage) },
  { path: 'checkout', loadComponent: () => import('./pages/checkout.page').then(m => m.CheckoutPage), canActivate:[authGuard] },
  { path: 'orders', loadComponent: () => import('./pages/orders.page').then(m => m.OrdersPage), canActivate:[authGuard] },
  { path: 'orders/:id', loadComponent: () => import('./pages/order-detail.page').then(m => m.OrderDetailPage), canActivate:[authGuard] },
  { path: 'auth/login', loadComponent: () => import('./pages/login.page').then(m => m.LoginPage) },
  { path: 'auth/register', loadComponent: () => import('./pages/register.page').then(m => m.RegisterPage) },
  { path: 'admin', loadComponent: () => import('./pages/admin.page').then(m => m.AdminPage), canActivate:[authGuard], data:{role:'ADMIN'} },
  { path: '**', redirectTo: 'home' }
];
