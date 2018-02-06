package me.lordmampf.CaravanOS;

import java.awt.Component;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		System.out.println("init..");

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

	//	public static final int WIDTH = 1200;
	//	public static final int HEIGHT = 400;

	public static JFrame mMainFrame;

	private static void createAndShowGUI() {

		//Create and set up the window.
	//	mMainFrame = new FullscreenFrame("CaravanOS");
	//	mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setPanel(new MenuPanel());
	}

	public static void setPanel(Component pPanel) {
		
		mMainFrame = new FullscreenFrame("CaravanOS");
		mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		mMainFrame.getContentPane().removeAll();
		mMainFrame.getContentPane().add(pPanel);
		
	//	mMainFrame.pack();
		/*
		 * mMainFrame.setVisible(true);
		 * mMainFrame.setResizable(true);
		 * mMainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 */

		fullScreen(mMainFrame, true);

	}

	static public boolean fullScreen(final JFrame frame, boolean doPack) {

		GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
		boolean result = device.isFullScreenSupported();

		if (result) {
			frame.setUndecorated(true);
			frame.setResizable(true);

			frame.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
					frame.setAlwaysOnTop(true);
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					frame.setAlwaysOnTop(false);
				}
			});

			if (doPack)
				frame.pack();

			device.setFullScreenWindow(frame);
		} else {
			frame.setPreferredSize(frame.getGraphicsConfiguration().getBounds().getSize());

			if (doPack)
				frame.pack();

			frame.setResizable(true);

			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			boolean successful = frame.getExtendedState() == Frame.MAXIMIZED_BOTH;

			frame.setVisible(true);

			if (!successful)
				frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		}
		return result;
	}

}
