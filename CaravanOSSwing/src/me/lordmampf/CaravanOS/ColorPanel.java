package me.lordmampf.CaravanOS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ColorPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage mImage;
	private int[] mPins;

	public ColorPanel(int pWidth, int pHeight) {

		mImage = getColorGradient(pWidth, pHeight);

		mPins = CurrentData.mConfigPins[CurrentData.mCurrentLight];

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int rgb = mImage.getRGB(e.getX(), e.getY());
				Color c = new Color(rgb);
				I2CHelper.setColor(mPins[0], mPins[1], mPins[2], c);
			};
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (mImage != null) {
			g.drawImage(mImage, 0, 0, this);
		}
	}

	public BufferedImage getColorGradient(int pWidth, int pHeight) {

		BufferedImage image = new BufferedImage(pWidth, pHeight, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < pWidth; x++) {
			for (int y = 0; y < pHeight; y++) {

				Color cpixel = Color.getHSBColor(x / (float) pWidth, 1, 1 - Math.min(y / (float) pHeight * 0.85f, 1.0f));

				image.setRGB(x, y, cpixel.getRGB());
			}
		}

		//	g.dispose();
		return image;
	}

	public int fromRGB(int r, int g, int b) {
		return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
	}

}
