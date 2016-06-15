package me.lordmampf.CaravanOS;

import javafx.scene.paint.Color;

public class I2CHelper {

	//@formatter:off
	/*
	 * 2 byte will be transferred
	 * TT00 PPPP XXXX XXXX
	 *
	 * TT is Type
	 *     00 Set Pin to high or low
	 * 	   01 Set PWM to Port
	 * PPPP Pin
	 * 	   0001 is Pin 1 etc.
	 * 
	 * XXXX XXXX Data
	 *     if set high or low
	 *     if PWM 0- 256 Value
	 */
	//@formatter:on

	public static void set(int pPin, boolean pHigh) {
		int value = 0b0100000000000000;

		if (pHigh)
			value |= 1;

		value |= pPin << 8;
		sendToArduino(value);
	}

	public static void setColor(int pFirstPin, Color pColor) {
		setPWM(pFirstPin, (int) (pColor.getRed() * 255));
		setPWM(pFirstPin + 1, (int) (pColor.getGreen() * 255));
		setPWM(pFirstPin + 2, (int) (pColor.getBlue() * 255));
	}

	public static void setPWM(int pPin, int pValue) {
		if (pValue < 0 || pValue > 255)
			return; //maybe exception

		int value = 0b0100000000000000;
		value |= pValue;
		value |= pPin << 8;

		sendToArduino(value);
	}

	private static void sendToArduino(int pData) {
		System.out.println(String.format("%16s", Integer.toBinaryString(pData)).replace(" ", "0"));
	}

}
