package day_1;

import java.io.IOException;

public class WeatherCalibratorDemo {
	public static void main(String[] args) throws IOException {
		WeatherCalibrator wc = new WeatherCalibrator();

		System.out.println(wc.calibrate("src/main/resources/day_1/input.txt"));
	}
}
