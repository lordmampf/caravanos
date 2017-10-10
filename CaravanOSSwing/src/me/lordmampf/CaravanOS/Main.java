package me.lordmampf.CaravanOS;

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

	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;

	public static JFrame mMainFrame;

	private static void createAndShowGUI() {

		//Create and set up the window.
		mMainFrame = new JFrame("CaravanOS");
		mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//	ColorPanel cp = new ColorPanel(WIDTH - 100, HEIGHT - 50);

		mMainFrame.getContentPane().add(new MenuPanel(WIDTH, HEIGHT));

		//Display the window.
		mMainFrame.pack();
		mMainFrame.setVisible(true);
		mMainFrame.setResizable(false);
		mMainFrame.setSize(WIDTH, HEIGHT);

	}

}
