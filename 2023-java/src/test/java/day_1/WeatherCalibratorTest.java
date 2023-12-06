package day_1;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class WeatherCalibratorTest {

	@Test
	public void testRowBasic() {
		WeatherCalibrator wc = new WeatherCalibrator();

		assertEquals(12, wc.parseLine("1abc2"));
		assertEquals(38, wc.parseLine("pqr3stu8vwx"));
		assertEquals(15, wc.parseLine("a1b2c3d4e5f"));
		assertEquals(77, wc.parseLine("treb7uchet"));
	}

	@Test
	public void testRowAdvanced() {
		WeatherCalibrator wc = new WeatherCalibrator();

		assertEquals(29, wc.parseLine("two1nine"));
		assertEquals(83, wc.parseLine("eightwothree"));
		assertEquals(13, wc.parseLine("abcone2threexyz"));
		assertEquals(24, wc.parseLine("xtwone3four"));
		assertEquals(42, wc.parseLine("4nineeightseven2"));
		assertEquals(14, wc.parseLine("zoneight234"));
		assertEquals(76, wc.parseLine("7pqrstsixteen"));
		assertEquals(38, wc.parseLine("37dqpbmqxssvznrzp2nvzcvlnsdoneightq"));
	}

	@Test
	public void basicTest() throws IOException {
		WeatherCalibrator weatherCalibrator = new WeatherCalibrator();

		assertEquals(142, weatherCalibrator.calibrate("src/test/resources/day_1/ex1.txt"));
		assertEquals(281, weatherCalibrator.calibrate("src/test/resources/day_1/ex2.txt"));
	}
}
