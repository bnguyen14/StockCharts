import { Component, OnInit } from '@angular/core';
import { ChartserviceService } from '../chartservice.service';
import * as CanvasJS from '../../../assets/canvasjs.min.js';
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
  constructor(private ChartService:ChartserviceService) { }

  ngOnInit() {
    this.ChartService.getChartData().subscribe(
      data => {
        console.log(data);
        this.chartdata=data;
        // console.log(this.chartdata.MetaData);
        // console.log(this.chartdata.TimeSeries.list);
        this.ticker=this.chartdata.MetaData.Symbol;
        this.chartdata.TimeSeries.list.forEach(element => {
          let x = element.time.split(" ");
          let date = x[0].split("-");
          let time = x[1].split(":");
          let year = Number.parseInt(date[0]);
          let month = Number.parseInt(date[1])-1;
          let day = Number.parseInt(date[2]);
          let hour = Number.parseInt(time[0]);
          let minute = Number.parseInt(time[1]);
          let second = Number.parseInt(time[2]);
          this.dataPoints.push({
            label: CanvasJS.formatDate(new Date(year,month,day,hour,minute,second,0)),
            y: [
              element.open,
              element.high,
              element.low,
              element.close
            ]
          });
        });
        // this.dataPoints=this.chartdata.TimeSeries.list;
        // console.log(this.ticker);
        // console.log(this.dataPoints);
        this.renderChart();
      }
    );

		
  }
  renderChart(){
    let chart = new CanvasJS.Chart("chartContainer", {
		  animationEnabled: false,
		  exportEnabled: true,
		  title: {
		  	text: this.ticker
      },
      axisY: {
        includeZero:false,
        prefix: "$ "
      },
      axisX: {
        intervalType: "day"
      },
      zoomEnabled:true,
		  data: [{
        type: "candlestick",
        yValueFormatString: "$##0.00",
        connectNullData: true,
		  	dataPoints: this.dataPoints,
      }]
    });
    chart.render();
  }
}
