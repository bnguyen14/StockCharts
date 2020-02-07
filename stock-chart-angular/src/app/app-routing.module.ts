import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CandlestickComponent } from './charts/candlestick/candlestick.component';

const routes: Routes = [
  {path:'CandlestickComponent', component:CandlestickComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
