package me.lordmampf.CaravanOS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LedStripePanel extends JPanel implements ActionListener, ChangeListener {

	private static final long serialVersionUID = 1L;

	private int[] mPins;
	private JSlider mSlider;

	public LedStripePanel() {

		mPins = CurrentData.mConfigPins[CurrentData.mCurrentLight];

		setLayout(new GridBagLayout());

		{
			JLabel lbl = new JLabel(CurrentData.getCurrentTitle());
			lbl.setFont(new Font("Serif", Font.PLAIN, 20));
			//			lbl.setMinimumSize(new Dimension(WIDTH, pHeight / 2));
			lbl.setBackground(Color.RED);
			//	lbl.setHorizontalAlignment(SwingConstants.CENTER);

			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.PAGE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			//	c.weightx = 10;
			//	c.weighty = 10;
			c.gridx = 0;
			c.gridy = 0;
			//	c.gridwidth = 3;
			add(lbl, c);
		}

		{
			ColorPanel cp = new ColorPanel();

			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 5;
			c.weighty = 5;
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 3;
			add(cp, c);
		}

		{

			mSlider = new JSlider(JSlider.VERTICAL, 0, 255, 0);
			mSlider.addChangeListener(this);
			mSlider.setMajorTickSpacing(10);
			mSlider.setPaintTicks(true);

			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.LINE_END;
			c.fill = GridBagConstraints.VERTICAL;
			c.weightx = 0.1;
			c.weighty = 0.1;
			c.gridx = 4;
			c.gridy = 2;
			//	c.gridwidth = 3;
			add(mSlider, c);
		}

		{
			JButton btn1 = new JButton("Licht ein");
			btn1.addActionListener(this);
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1;
			c.gridx = 0;
			c.gridy = 3;
			add(btn1, c);
		}

		{
			JButton btn2 = new JButton("Licht aus");
			btn2.addActionListener(this);
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1;
			c.gridx = 1;
			c.gridy = 3;
			add(btn2, c);
		}

		{
			JButton exit = new JButton("Zurück");

			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.4;
			c.gridx = 2;
			c.gridy = 3;
			add(exit, c);

			exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					SwingUtilities.invokeLater(() -> {
						Main.setPanel(new MenuPanel());
					});
				}
			});
		}

	}

	public void actionPerformed(ActionEvent pActionEvent) {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	@Override
	public void stateChanged(ChangeEvent arg0) {

		int value = mSlider.getValue();
		I2CHelper.setPWM(mPins[3], value);

	}

}
