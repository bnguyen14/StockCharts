package pyramid.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaData {
	@JsonProperty("Information")
	@JsonAlias("1. Information")
	private String Information;
	
	@JsonProperty("Symbol")
	@JsonAlias("2. Symbol")
	private String Symbol;
	
	@JsonProperty("LastRefreshed")
	@JsonAlias("3. Last Refreshed")
	private String LastRefreshed;
	
	@JsonProperty("Interval")
	@JsonAlias("4. Interval")
	private String Interval;
	
	@JsonProperty("OutputSize")
	@JsonAlias("5. Output Size")
	private String OutputSize;
	
	@JsonProperty("TimeZone")
	@JsonAlias("6. Time Zone")
	private String TimeZone;
	
	public MetaData() {}

	@Override
	public String toString() {
		return "MetaData [Information=" + Information + ", Symbol=" + Symbol + ", LastRefreshed=" + LastRefreshed
				+ ", Interval=" + Interval + ", OutputSize=" + OutputSize + ", TimeZone=" + TimeZone + "]";
	}
}
