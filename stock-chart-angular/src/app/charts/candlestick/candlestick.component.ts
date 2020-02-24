import { Component, OnInit, HostListener } from '@angular/core';
import { ChartserviceService } from '../chartservice.service';
import { createChart } from 'lightweight-charts';
import { Chartdata } from '../chartdata';

@Component({
  selector: 'app-candlestick',
  templateUrl: './candlestick.component.html',
  styleUrls: ['./candlestick.component.css']
})
export class CandlestickComponent implements OnInit {
  ticker = "aapl";
  dataPoints = [];
  chartdata:Chartdata;
  chart;
  candlestickSeries;
  constructor(private ChartService:ChartserviceService) { }

  ngOnInit() {
    this.ChartService.getSwingChartData().subscribe(
      data => {
        console.log(data);
        this.chartdata=data;
        // console.log(this.chartdata.MetaData);
        // console.log(this.chartdata.TimeSeries.list);
        this.ticker=this.chartdata.MetaData.Symbol;
        this.chartdata.TimeSeries.list.forEach(element => {
          if(this.chartdata.MetaData.Interval){
            this.dataPoints.push({
              time: +element.time,
              open: element.open,
              high: element.high,
              low: element.low,
              close: element.close
            });
          }else{
            this.dataPoints.push({
              time: element.time,
              open: element.open,
              high: element.high,
              low: element.low,
              close: element.close
            });
          }
          
        });
        this.setChart();
      }
    );
  }
  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.chart.resize(300,window.innerWidth);
  }
  setChart(){
    this.chart = createChart(document.body, { width: window.innerWidth, height: 300 });
    this.candlestickSeries = this.chart.addCandlestickSeries();
    this.candlestickSeries.setData(this.dataPoints);
  }
}
