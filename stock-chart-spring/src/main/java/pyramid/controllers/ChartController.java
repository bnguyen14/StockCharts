package pyramid.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pyramid.models.ChartData;
import pyramid.models.RespModel;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/chart")
public class ChartController {
	
	@Value("${api.key}")
    private String apiKey;
	
	@GetMapping(value = "/intraday/{symbol}/{interval}")
	public ResponseEntity<RespModel> getIntradayChartData(@PathVariable String symbol, @PathVariable String interval) {
		System.out.println("requested intraday");
		String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&outputsize=full&apikey="+apiKey;
//		String url = "https://www.alphavantage.co/query?"
//				+ "function=TIME_SERIES_INTRADAY"
//				+ "&symbol="+symbol
//				+ "&interval="+interval
//				+ "&apikey="+apiKey;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		RespModel rm = null;
		try {
			rm = mapper.readValue(response.getBody(), RespModel.class);
			JSONObject obj = new JSONObject(response.getBody());
			JSONObject timeseries = obj.getJSONObject(JSONObject.getNames(obj)[0]);
			String[] series = JSONObject.getNames(timeseries);
			//System.out.println(timeseries.toString());
			Arrays.sort(series);
			for(int i = 0; i< series.length; i++) {
				rm.getTimeSeries().addToSeries(series[i], mapper.readValue(
						timeseries.getJSONObject(series[i]).toString(), ChartData.class));
			}
//			System.out.println(rm.toString());
//			System.out.println(rm.getTimeSeries());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		return new ResponseEntity<RespModel>(rm, HttpStatus.OK);
	}
	
	@GetMapping(value = "/swing/{symbol}/{interval}")
	public void getLongChartData(@PathVariable String symbol, @PathVariable String interval) {
		String url = "https://www.alphavantage.co/query?"
				+ "function=TIME_SERIES_"+interval.toUpperCase()
				+ "&symbol="+symbol
				+ "&apikey="+apiKey;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
	}
}
