# ecommerce-microservices-app

[![Java](https://img.shields.io/badge/Java-17-orange)](https://openjdk.org/projects/jdk/17/) [![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F)](https://spring.io/projects/spring-boot) [![Angular](https://img.shields.io/badge/Angular-17-DD0031)](https://angular.dev/) [![NgRx](https://img.shields.io/badge/NgRx-Store-BA2BD2)](https://ngrx.io/) [![TypeScript](https://img.shields.io/badge/TypeScript-5.x-3178C6)](https://www.typescriptlang.org/) [![H2](https://img.shields.io/badge/Database-H2-1A1A1A)](https://www.h2database.com/) [![Maven](https://img.shields.io/badge/Build-Maven-C71A36)](https://maven.apache.org/)

Production-grade full-stack portfolio E-Commerce platform built to demonstrate **enterprise Java + Angular expertise** for Gourav Mishra.

## Architecture

```text
┌──────────────────────────────────────────────────────────────┐
│                     Angular 17 Frontend                     │
│  Routes + Lazy Loading + NgRx + Angular Material + Chart.js │
└───────────────┬──────────────────────────────────────────────┘
                │ HTTP + JWT
┌───────────────▼──────────────────────────────────────────────┐
│                 Spring Boot 3 REST Backend                  │
│ Auth (JWT/RBAC) | Products | Cart | Orders | Admin Dashboard│
└───────────────┬──────────────────────────────────────────────┘
                │ Spring Data JPA / Hibernate
┌───────────────▼──────────────────────────────────────────────┐
│                         H2 In-Memory                        │
└──────────────────────────────────────────────────────────────┘
```

## Run locally

### Backend (port 8080)
```bash
cd backend
mvn spring-boot:run
```
Swagger UI: `http://localhost:8080/swagger-ui.html`

### Frontend (port 4200)
```bash
cd frontend
npm install
npm run start -- --port 4200
```

## Build locally

### Backend
```bash
cd backend
mvn clean verify
```

### Frontend
```bash
cd frontend
npm ci
npm run build
```

## Push all code to GitHub (step-by-step)

1. Create a GitHub repository named **`ecommerce-microservices-app`**.
2. In your local project root, run:

```bash
git init
git branch -M main
git add .
git commit -m "Initial production-grade ecommerce app"
git remote add origin https://github.com/gouravm19/ecommerce-microservices-app.git
git push -u origin main
```

If your repository already exists locally and has commits, just run:

```bash
git remote add origin https://github.com/gouravm19/ecommerce-microservices-app.git
# or: git remote set-url origin https://github.com/gouravm19/ecommerce-microservices-app.git
git push -u origin main
```

## Make it live (recommended portfolio deployment)

### Option A — Deploy Backend (Render) + Frontend (Vercel/Netlify)

#### 1) Backend on Render
- Create a new **Web Service** from this repo.
- Root directory: `backend`
- Build command: `mvn clean package`
- Start command: `java -jar target/ecommerce-backend-1.0.0.jar`
- Add environment variable if needed:
  - `JAVA_VERSION=17`

#### 2) Frontend on Vercel
- Create new project from same repo.
- Root directory: `frontend`
- Build command: `npm ci && npm run build`
- Output directory: `dist/frontend/browser` (or `dist/frontend` depending on builder)
- Set environment variable in frontend build (if using file replacement/runtime config) for API base URL to your Render backend URL.

### Option B — GitHub Pages (frontend only)
If you only want static UI hosting, deploy `frontend` to GitHub Pages. (Backend APIs must still run elsewhere.)

## GitHub Actions CI
Pipeline file: `.github/workflows/ci.yml`
- Trigger: push to `main`
- Builds backend with Maven
- Builds frontend with npm
- Runs tests in CI

> Note: for Angular unit tests in CI, use a runner with Chrome/Chromium installed for `ChromeHeadless`.

## Seeded demo data
- Categories: Electronics, Clothing, Books, Sports, Home
- 20 sample products
- Admin: `admin@gouravmishra.dev / Admin@123`
- Customer: `customer@test.com / Test@123`
- 5 sample orders in different statuses

## API endpoints
- Auth: `/api/auth/register`, `/api/auth/login`, `/api/auth/profile`
- Products: `/api/products`, `/api/products/{id}`, `/api/products/search?q=...`
- Cart: `/api/cart`, `/api/cart/add`, `/api/cart/update/{id}`, `/api/cart/remove/{id}`, `/api/cart/clear`
- Orders: `/api/orders/checkout`, `/api/orders`, `/api/orders/{id}`, `/api/orders/{id}/status`
- Dashboard: `/api/dashboard/stats`

## Screenshots
- Home page: dark hero, featured catalog entry point
- Product listing: responsive cards with hover lift + CTA
- Admin dashboard: stat cards with chart widgets

---
Built by **Gourav Mishra** — **gouravmishra.is-a.dev**
