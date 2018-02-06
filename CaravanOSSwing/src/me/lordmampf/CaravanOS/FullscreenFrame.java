package me.lordmampf.CaravanOS;

import javax.swing.JFrame;

public class FullscreenFrame extends JFrame {

	public FullscreenFrame(String pTitle) {
		super(pTitle);
		setUndecorated(true);
	}
}
