import { AfterViewInit, Component, Input } from '@angular/core';
import { Chart } from 'chart.js/auto';

@Component({selector:'app-admin-stats-card',standalone:true,template:`<div class='card'><h4>{{title}}</h4><canvas [id]='id'></canvas></div>`,styles:[`.card{padding:1rem;background:#11192a;border:1px solid #ffffff22;border-radius:12px}`]})
export class AdminStatsCardComponent implements AfterViewInit{
  @Input() title='Stat'; @Input() id='chart'; @Input() value=0;
  ngAfterViewInit(): void { new Chart(this.id,{type:'bar',data:{labels:['Metric'],datasets:[{label:this.title,data:[this.value],backgroundColor:'#00e5ff'}]}}); }
}
