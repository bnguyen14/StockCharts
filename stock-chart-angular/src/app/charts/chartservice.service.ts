import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Chartdata } from './chartdata';
@Injectable({
  providedIn: 'root'
})
export class ChartserviceService {

  constructor(private httpClient : HttpClient) { }
  //https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo
  getChartData(){
    return this.httpClient.get<Chartdata>("http://localhost:8080/chart/intraday/TSLA/1min");
  }
}
