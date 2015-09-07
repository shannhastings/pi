package com.shannonhastings.piwater;

import com.pi4j.wiringpi.SoftPwm;

public class Motor {
	public static int FORWARD = 1;
	public static int STOPPED = 0;
	public static int REVERSE = -1;

	private static int POWER_INCREMENT = -5;
	private static int POWER_MIN = 0;
	private static int POWER_MAX = 100;

	private int direction;
	private int power;
	private int motorNumber;
	private int motorPin1;
	private int motorPin2;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getMotorNumber() {
		return motorNumber;
	}

	public void setMotorNumber(int motorNumber) {
		this.motorNumber = motorNumber;
	}

	public int getMotorPin1() {
		return motorPin1;
	}

	public void setMotorPin1(int motorPin1) {
		SoftPwm.softPwmCreate(motorPin1,POWER_MIN, POWER_MAX);
		this.motorPin1 = motorPin1;
	}

	public int getMotorPin2() {
		return motorPin2;
	}

	public void setMotorPin2(int motorPin2) {
		SoftPwm.softPwmCreate(motorPin2, POWER_MIN, POWER_MAX);
		this.motorPin2 = motorPin2;
	}

	public synchronized void configureMotor(int direction, int speed) throws InterruptedException {

		speed = Math.round(speed / POWER_INCREMENT) * POWER_INCREMENT;

		if (getDirection() != direction && getDirection() != STOPPED) {
			stop();
			Thread.sleep(500);
		}
		if (direction == FORWARD) {
			SoftPwm.softPwmWrite(getMotorPin1(), speed);
			setDirection(direction);
			setPower(speed);
			System.out.println("Speed is set to forward at " + speed + "%");
		} else if (direction == REVERSE) {
			SoftPwm.softPwmWrite(getMotorPin2(), speed);
			System.out.println("Speed is set to reverse at " + speed + "%");
			setDirection(direction);
			setPower(speed);
		} else {
			System.out.println("MOTOR not going to change speed because it is stopped and not in a direction.");
		}
	}
	
	public synchronized void stop() throws InterruptedException {
		SoftPwm.softPwmWrite(getMotorPin1(), 0);
		SoftPwm.softPwmWrite(getMotorPin2(), 0);
		setDirection(STOPPED);
		setPower(0);
	}

	public synchronized void faster() throws InterruptedException {
		if (getPower() != 0 && getPower()<POWER_MAX) {
			if (getDirection() == FORWARD) {
				SoftPwm.softPwmWrite(getMotorPin1(), getPower() + POWER_INCREMENT);
				setPower(getPower() + 5);
			} else if (getDirection() == REVERSE) {
				SoftPwm.softPwmWrite(getMotorPin2(), getPower() + POWER_INCREMENT);
				setPower(getPower() + 5);
			} else {
				System.out.println("MOTOR not going to change speed because it is stopped and not in a direction.");
			}
		} else {
			System.out.println("MOTOR not going to change speed because it is stopped.");
		}
	}

	public synchronized void slower() throws InterruptedException {
		if (getPower() != 0  && getPower()>POWER_MIN) {
			if (getDirection() == FORWARD) {
				SoftPwm.softPwmWrite(getMotorPin1(), getPower() - POWER_INCREMENT);
				setPower(getPower() - 5);
			} else if (getDirection() == REVERSE) {
				SoftPwm.softPwmWrite(getMotorPin2(), getPower() - POWER_INCREMENT);
				setPower(getPower() - 5);
			} else {
				System.out.println("MOTOR not going to change speed because it is stopped and not in a direction.");
			}
		} else {
			System.out.println("MOTOR not going to change speed because it is stopped.");
		}
	}

	@Override
	public String toString() {
		return "Motor [direction=" + direction + ", power=" + power + ", motorNumber=" + motorNumber + ", motorPin1=" + motorPin1 + ", motorPin2=" + motorPin2
				+ "]";
	}
	
	

}
