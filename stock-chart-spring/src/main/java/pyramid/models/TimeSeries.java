package pyramid.models;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeries {
	@JsonProperty("list")
	List<ChartData> chartList;

	public TimeSeries() {
		chartList = new LinkedList<>();
	}
	
	public void addToSeries(String time, ChartData chartData) {
		chartData.setTime(time);
		chartList.add(chartData);
	}
	@Override
	public String toString() {
		String tmp = "";
		for(ChartData data : this.chartList) {
			tmp += data.toString() + "\n";
		}
		return tmp;
	}
}
