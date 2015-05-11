package rpi.examples.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LedGpioExample {

	public static void main(String[] args) throws InterruptedException {

		final GpioController gpio = GpioFactory.getInstance();

		final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "MyLED", PinState.HIGH);
		pin.setShutdownOptions(true, PinState.LOW);

		Thread.sleep(5000);

		pin.low();
		Thread.sleep(5000);
		pin.toggle();
		Thread.sleep(5000);
		pin.toggle();
		Thread.sleep(5000);

		pin.pulse(1000, true); // set second argument to 'true' use a blocking
							   // call

		gpio.shutdown();

	}
}
