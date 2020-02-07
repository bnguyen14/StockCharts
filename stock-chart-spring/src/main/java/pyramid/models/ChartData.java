package pyramid.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChartData {
	@JsonProperty("time")
	private String time;
	
	@JsonProperty("open")
	@JsonAlias("1. open")
	private float open;
	
	@JsonProperty("high")
	@JsonAlias("2. high")
	private float high;
	
	@JsonProperty("low")
	@JsonAlias("3. low")
	private float low;
	
	@JsonProperty("close")
	@JsonAlias("4. close")
	private float close;
	
	@JsonProperty("volume")
	@JsonAlias("5. volume")
	private float volume;
	
	public ChartData() {}

	public ChartData(String time, float open, float high, float low, float close, float volume) {
		super();
		this.time = time;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ChartData [time=" + time + ", open=" + open + ", high=" + high
				+ ", low=" + low + ", close=" + close + ", volume=" + volume + "]";
	}
	
	
}
