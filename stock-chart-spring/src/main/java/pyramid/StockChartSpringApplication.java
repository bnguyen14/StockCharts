package pyramid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pyramid.controllers.ChartController;

@SpringBootApplication
public class StockChartSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockChartSpringApplication.class, args);
//		ChartController cc = new ChartController();
//		cc.getIntradayChartData("test", "test");
	}

}
