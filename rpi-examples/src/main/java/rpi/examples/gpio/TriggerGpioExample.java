package rpi.examples.gpio;

import java.util.concurrent.Callable;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;

public class TriggerGpioExample {

	public static void main(String[] args) throws InterruptedException {

		final GpioController gpio = GpioFactory.getInstance();

		// provision input pins for button
		// per pin wiring http://pi4j.com/example/control.html this is pin
		// 13/GPIO2/2 - #27
		final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
		// provision output pins
		// per pin wiring http://pi4j.com/example/control.html this is pin
		// 16/GPIO4/4 - #23
		GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "MyLED", PinState.LOW);
		myLed.setShutdownOptions(true, PinState.LOW);

		// add trigger to the button to set state high on myLed when button
		// input on pin 02 goes high
		myButton.addTrigger(new GpioSetStateTrigger(PinState.HIGH, myLed, PinState.HIGH));

		// add trigger to the button to set state low on myLed when button
		// input on pin 02 goes low
		myButton.addTrigger(new GpioSetStateTrigger(PinState.LOW, myLed, PinState.LOW));

		// create a gpio callback trigger
		myButton.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
			public Void call() throws Exception {
				System.out.println(" --> GPIO TRIGGER CALLBACK RECEIVED ");
				return null;
			}
		}));

		for (int i = 0; i < 100; i++) {
			Thread.sleep(500);
		}

		gpio.shutdown();
	}
}
