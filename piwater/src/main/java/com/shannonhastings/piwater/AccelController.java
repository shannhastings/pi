package com.shannonhastings.piwater;

import com.pi4j.component.gyroscope.Gyroscope;
import com.pi4j.component.gyroscope.analogdevices.ADXL345;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.wiringpi.Gpio;

public class AccelController {
	
	private GpioController gpio;
	

	public AccelController() {
		Gpio.wiringPiSetup();
		gpio = GpioFactory.getInstance();
	}
	
	public void init() throws InterruptedException {
		System.out.println("Started");
		
		System.out.println("Finished");
	}
	
	protected void test () throws InterruptedException {

	}

	 public static void main(String[] args) throws Exception {
	        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);

	        ADXL345 adxl345 = new ADXL345(bus);

	        adxl345.init(adxl345.X, Gyroscope.GET_RAW_VALUE_TRIGGER_READ);
	        adxl345.recalibrateOffset();

	        long now = System.currentTimeMillis();
	        
	        int measurement = 0;
	        
	        while (System.currentTimeMillis() - now < 10000) {
	            
	            adxl345.readGyro();
	            
	            String sm = adxl345.toString(measurement, 3);
	            
	            String sx = adxl345.toString(adxl345.X.getRawValue(), 7);
	            String sy = adxl345.toString(adxl345.Y.getRawValue(), 7);
	            String sz = adxl345.toString(adxl345.Z.getRawValue(), 7);
	            
	            System.out.print(sm + sx + sy + sz);
	            for (int i = 0; i < 24; i++) { System.out.print((char)8); }
	            
	            Thread.sleep(100);
	            
	            measurement++;
	        }
	        System.out.println();
	    }
	
}
