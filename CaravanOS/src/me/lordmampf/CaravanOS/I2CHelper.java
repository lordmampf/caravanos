package me.lordmampf.CaravanOS;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javafx.scene.paint.Color;

public class I2CHelper {

	private static Queue<Integer> mSendQueue = new ConcurrentLinkedQueue<Integer>();

	private static Runnable mRunnable = new Runnable() {
		public void run() {

			while (true) {
				while (!mSendQueue.isEmpty()) {
					final Integer data = mSendQueue.poll();
					System.out.println(String.format("%16s", Integer.toBinaryString(data)).replace(" ", "0"));

					try {
						Process p = Runtime.getRuntime().exec("/usr/bin/python /home/manu/Desktop/i2c.py " + data);
						p.waitFor();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private static Thread mRunningThread = new Thread(mRunnable);

	static {
		mRunningThread.start();
	}

	//@formatter:off
	/*
	 * 2 byte will be transferred
	 * TT11 PPPP XXXX XXXX
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
		int value = 0b0111000000000000;

		if (pHigh)
			value |= 1;

		value |= pPin << 8;
		sendToArduino(value);
	}

	public static void setColor(int pPinR, int pPinG, int pPinB, Color pColor) {
		setPWM(pPinR, (int) (pColor.getRed() * 255));
		setPWM(pPinG, (int) (pColor.getGreen() * 255));
		setPWM(pPinB, (int) (pColor.getBlue() * 255));
	}

	public static void setPWM(int pPin, int pValue) {
		if (pValue < 0 || pValue > 255)
			return; //maybe exception

		int value = 0b0111000000000000;
		value |= pValue;
		value |= pPin << 8;

		sendToArduino(value);
	}

	private static void sendToArduino(int pData) {
		mSendQueue.add(pData);
	}

}
