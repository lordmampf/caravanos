package me.lordmampf.CaravanOS;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class I2CHelper {

	private static Queue<String> mSendQueue = new ConcurrentLinkedQueue<String>();

	private static Runnable mRunnable = new Runnable() {
		public void run() {

			runComamnd("/home/led/init.sh");

			while (true) {
				while (!mSendQueue.isEmpty()) {
					final String cmd = mSendQueue.poll();
					runComamnd("/home/led/pwm.sh " + cmd);
				}

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private static void runComamnd(String pCommand) {
		System.out.println("runComamnd " + pCommand);

		if (!System.getProperty("os.name").toLowerCase().contains("win")) {
			try {
				Process p = Runtime.getRuntime().exec(pCommand);

				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}

				p.waitFor();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static Thread mRunningThread = new Thread(mRunnable);

	static {
		mRunningThread.setDaemon(true);
		mRunningThread.start();
	}

	public static void setColor(int pPinR, int pPinG, int pPinB, Color pColor) {
		setPWM(pPinR, pColor.getRed());
		setPWM(pPinG, pColor.getGreen());
		setPWM(pPinB, pColor.getBlue());
	}

	private static int map(int x, int in_min, int in_max, int out_min, int out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	public static void setPWM(int pPin, int pValue) {
		if (pValue < 0 || pValue > 255)
			return; //maybe exception

		pValue = map(pValue, 0, 255, 14, 24);

		mSendQueue.add(pPin + " " + pValue);
	}

}
