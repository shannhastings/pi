package com.shannonhastings.piwater;

import java.io.IOException;
import java.text.ParseException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class MotorController {

	private GpioController gpio;
	private static int PIN_NUMBER = 1;

	public MotorController() {
		gpio = GpioFactory.getInstance();
	}

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		System.out.println("Started");
		// initialize wiringPi library, this is needed for PWM
		Gpio.wiringPiSetup();
		// softPwmCreate(int pin, int value, int range)
		// the range is set like (min=0 ; max=100)
		SoftPwm.softPwmCreate(PIN_NUMBER, 0, 100);
		setSpeed(25);
		setSpeed(50);
		setSpeed(100);
		setSpeed(0);
		System.out.println("Finished");
	}

	private static void setSpeed(int speed) throws InterruptedException {
		System.out.println("Speed is set to " + speed + "%");
		// softPwmWrite(int pin, int value)
		// This updates the PWM value on the given pin. The value is checked to
		// be in-range and pins that haven't previously been initialized via
		// softPwmCreate will be silently ignored.
		SoftPwm.softPwmWrite(PIN_NUMBER, speed);
		// wait 3 seconds
		Thread.sleep(3000);
	}
}
