package com.shannonhastings.piwater;

import java.io.IOException;
import java.text.ParseException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class MotorController {
	
	private GpioController gpio;
	private static int MOTOR_1 = 1;
	private static int MOTOR_2 = 2;
	private static int MOTOR_1_PIN_1 = 12;
	private static int MOTOR_1_PIN_2 = 16;
	private static int MOTOR_2_PIN_1 = 20;
	private static int MOTOR_2_PIN_2 = 21;

	private Motor motor1;
	private Motor motor2;

	public MotorController() {
		Gpio.wiringPiSetup();
		gpio = GpioFactory.getInstance();
	}
	
	public void init() throws InterruptedException {
		System.out.println("Started");
		motor1 = new Motor();
		motor1.setMotorNumber(MOTOR_1);
		motor1.setMotorPin1(MOTOR_1_PIN_1);
		System.out.println("PIN 1 SET");
		motor1.setMotorPin2(MOTOR_1_PIN_2);
		System.out.println("PIN_2_SET");
		System.out.println(motor1);
//		motor2 = new Motor();
//		motor2.setMotorNumber(MOTOR_2);
//		motor2.setMotorPin1(MOTOR_2_PIN_1);
//		motor2.setMotorPin2(MOTOR_2_PIN_2);
//		System.out.println(motor2);
		System.out.println("Finished");
	}
	
	protected void test () throws InterruptedException {
		motor1.configureMotor(Motor.FORWARD, 25);
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.faster();
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.faster();
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.slower();
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.slower();
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.configureMotor(Motor.FORWARD, 50);
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.configureMotor(Motor.FORWARD, 100);
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.configureMotor(Motor.FORWARD, 0);
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.configureMotor(Motor.REVERSE, 25);
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.faster();
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.faster();
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.slower();
		System.out.println(motor1);
		Thread.sleep(500);
		motor1.slower();
		motor1.configureMotor(Motor.REVERSE, 0);
		System.out.println(motor1);
		Thread.sleep(500);
	}

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		MotorController controller = new MotorController();
		controller.init();
		controller.test();
	}
	
}
