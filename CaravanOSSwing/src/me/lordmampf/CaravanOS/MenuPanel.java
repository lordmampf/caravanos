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

	private JButton mButton1, mButton2, mButton3, mButton4;

	private static final int mTextSize = 40;

	public MenuPanel() {

		GridLayout layout = new GridLayout(8, 1);

		setLayout((LayoutManager) layout);

		JLabel lbl = new JLabel("Wohnwagen Halts Maul!");
		lbl.setFont(new Font("Serif", Font.PLAIN, 40));

		//		lbl.setMinimumSize(new Dimension(WIDTH, pHeight / 2));
		lbl.setBackground(Color.RED);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);

		mButton1 = new JButton(CurrentData.getTitle(0));
		mButton2 = new JButton(CurrentData.getTitle(1));
		mButton3 = new JButton(CurrentData.getTitle(2));
		mButton4 = new JButton(CurrentData.getTitle(3));
		JButton exit = new JButton("Beenden");

		mButton1.setFont(new Font("Serif", Font.PLAIN, mTextSize));
		mButton2.setFont(new Font("Serif", Font.PLAIN, mTextSize));
		mButton3.setFont(new Font("Serif", Font.PLAIN, mTextSize));
		mButton4.setFont(new Font("Serif", Font.PLAIN, mTextSize));
		exit.setFont(new Font("Serif", Font.PLAIN, mTextSize));

		mButton1.addActionListener(this);
		mButton2.addActionListener(this);
		mButton3.addActionListener(this);
		mButton4.addActionListener(this);

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
		add(mButton3);
		add(mButton4);
		add(Box.createHorizontalGlue());
		add(exit);
	}

	public void actionPerformed(ActionEvent pActionEvent) {
		if (pActionEvent.getSource() == mButton1) {
			CurrentData.mCurrentLight = 0;
		} else if (pActionEvent.getSource() == mButton2) {
			CurrentData.mCurrentLight = 1;
		} else if (pActionEvent.getSource() == mButton3) {
			CurrentData.mCurrentLight = 2;
		} else if (pActionEvent.getSource() == mButton4) {
			CurrentData.mCurrentLight = 3;
		}

		SwingUtilities.invokeLater(() -> {
			Main.setPanel(new LedStripePanel());
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

}
