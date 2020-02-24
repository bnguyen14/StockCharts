package pyramid.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo";
//		String url = "https://www.alphavantage.co/query?"
//				+ "function=TIME_SERIES_INTRADAY"
//				+ "&symbol="+symbol
//				+ "&interval="+interval
//				+ "&apikey="+apiKey;
		return constructRespEntity("intraday", url);
	}
	
	@GetMapping(value = "/swing/{symbol}/{interval}")
	public ResponseEntity<RespModel> getLongChartData(@PathVariable String symbol, @PathVariable String interval) {
		String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=demo";
//		String url = "https://www.alphavantage.co/query?"
//				+ "function=TIME_SERIES_"+interval.toUpperCase()
//				+ "&symbol="+symbol
//				+ "&apikey="+apiKey;
		return constructRespEntity("swing", url);
	}
	
	private ResponseEntity<RespModel> constructRespEntity(String format, String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		RespModel rm = null;
		try {
			rm = mapper.readValue(response.getBody(), RespModel.class);
			JSONObject obj = new JSONObject(response.getBody());
			JSONObject timeseries = obj.getJSONObject(JSONObject.getNames(obj)[0]);
			String[] series = JSONObject.getNames(timeseries);
			Arrays.sort(series);
			for(int i = 0; i< series.length; i++) {
				rm.getTimeSeries().addToSeries(format.equals("intraday")?dateToUnix(series[i]):series[i],
						mapper.readValue(timeseries.getJSONObject(series[i]).toString(), ChartData.class));
			}
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
		return new ResponseEntity<RespModel>(rm, HttpStatus.OK);
	}
	
	private String dateToUnix(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return String.valueOf(sdf.parse(date).getTime()/1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "conversion error";
	}
}
