import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { AdminStatsCardComponent } from '../components/admin-stats-card.component';

@Component({standalone:true,imports:[AdminStatsCardComponent],template:`<h2>Admin Dashboard</h2><div class='grid'><app-admin-stats-card title='Orders' id='ordersChart' [value]='stats.totalOrders||0'></app-admin-stats-card><app-admin-stats-card title='Revenue' id='revChart' [value]='stats.totalRevenue||0'></app-admin-stats-card></div>`,styles:[`.grid{display:grid;grid-template-columns:1fr 1fr;gap:1rem}`]})
export class AdminPage implements OnInit{ stats:any={}; constructor(private api:ApiService){} ngOnInit(){ this.api.dashboard().subscribe(v=>this.stats=v); }}
