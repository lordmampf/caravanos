package me.lordmampf.CaravanOS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MenuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton mButton1, mButton2;

	public MenuPanel(int pWidth, int pHeight) {

		GridLayout layout = new GridLayout(6, 1);

		setLayout((LayoutManager) layout);

		JLabel lbl = new JLabel("Herzlich Willkommen!");
		lbl.setFont(new Font("Serif", Font.PLAIN, 40));
		lbl.setMinimumSize(new Dimension(WIDTH, pHeight / 2));
		lbl.setBackground(Color.RED);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);

		mButton1 = new JButton("Beleuchtung 1");
		mButton2 = new JButton("Beleuchtung 2");
		JButton exit = new JButton("Beenden");

		mButton1.addActionListener(this);
		mButton2.addActionListener(this);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add(lbl, SwingConstants.CENTER);
		add(Box.createHorizontalGlue());
		add(mButton1);
		add(mButton2);
		add(Box.createHorizontalGlue());
		add(exit);
	}

	public void actionPerformed(ActionEvent pActionEvent) {
		if (pActionEvent.getSource() == mButton1) {
			CurrentData.mCurrentLight = 0;
		} else if (pActionEvent.getSource() == mButton2) {
			CurrentData.mCurrentLight = 1;
		}

		SwingUtilities.invokeLater(() -> {
			Main.mMainFrame.getContentPane().removeAll();
			Main.mMainFrame.add(new ColorPanel(Main.WIDTH, Main.HEIGHT));
			Main.mMainFrame.pack();
			Main.mMainFrame.setSize(Main.WIDTH, Main.HEIGHT);
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

}
