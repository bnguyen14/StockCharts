package pyramid.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespModel {
	@JsonProperty("MetaData")
	@JsonAlias("Meta Data")
	private MetaData metaData;
	
	@JsonProperty("TimeSeries")
	@JsonAlias({"Time Series (1min)","Time Series (5min)","Time Series (15min)",
				"Time Series (30min)","Time Series (60min)",
				"Time Series (Daily)","Weekly Time Series","Monthly Time Series"})
	private TimeSeries timeSeries;
	
	public RespModel() {}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public TimeSeries getTimeSeries() {
		return timeSeries;
	}

	public void setTimeSeries(TimeSeries timeSeries) {
		this.timeSeries = timeSeries;
	}

	@Override
	public String toString() {
		return "RespModel [metaData=" + metaData + "\ntimeSeries=" + timeSeries + "]";
	}
	
	
}
