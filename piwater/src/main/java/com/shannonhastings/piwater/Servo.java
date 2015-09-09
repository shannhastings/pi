package com.shannonhastings.piwater;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.RaspiPin;

public class Servo {
	public static void main(String[] args) throws InterruptedException {
		final GpioController gpio = GpioFactory.getInstance();

		GpioPinPwmOutput pwm = gpio.provisionPwmOutputPin(RaspiPin.GPIO_24);
		pwm.setPwm(500);

		// keep program running until user aborts (CTRL-C)
		for (;;) {
			Thread.sleep(500);
		}
		// }
		//
		// }
		// //Servo sources:
		// //http://en.wikipedia.org/wiki/Servo_control
		// //http://upload.wikimedia.org/wikipedia/commons/b/b7/Sinais_controle_servomotor.JPG
		// //http://learn.adafruit.com/adafruits-raspberry-pi-lesson-8-using-a-servo-motor/servo-motors
		// //1000 == -90°
		// //1500 == 0°
		// //2000 == 90°
		//
		// while (true)
		// {
		//// for (int i=0; i<100; i++)
		//// {
		//// myServo.high();
		//// java.lang.Thread.sleep(0, 1000);
		//// myServo.low();
		//// java.lang.Thread.sleep(0, 19000);
		//// }
		////
		// for (int i=0; i<100; i++)
		// {
		// myServo.high();
		// java.lang.Thread.sleep(0, 2000);
		// myServo.low();
		// java.lang.Thread.sleep(0, 18000);
		// }
		// }

	}
}