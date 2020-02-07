import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CandlestickComponent } from './candlestick/candlestick.component';
import * as CanvasJS from '../../assets/canvasjs.min.js';


@NgModule({
  declarations: [CandlestickComponent],
  imports: [
    CommonModule
  ]
})
export class ChartsModule { }
