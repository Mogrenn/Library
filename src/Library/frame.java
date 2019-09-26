package Library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javaDB.javaDB;

public class frame extends JFrame {

	private Random rand = new Random();
	private SQL conn = new SQL();
	private JTextField txt;

	public frame() {

		this.setPreferredSize(new Dimension(600, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2, 1));

		JPanel upperContainer = new JPanel(new GridLayout(2, 1));

		txt = new JTextField(20);
		txt.setPreferredSize(new Dimension(150, 50));
		txt.setMaximumSize(new Dimension(200, 100));
		txt.setHorizontalAlignment(JTextField.CENTER);
		txt.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

		JPanel txtPanel = new JPanel();
		txtPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		txtPanel.setPreferredSize(new Dimension(300, 300));
		txtPanel.add(txt);

		upperContainer.add(txtPanel);

		JPanel upperInnerContainer = new JPanel(new GridLayout(1, 2));
		upperInnerContainer.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

		JPanel leftUpperInnerContainer = new JPanel();
		leftUpperInnerContainer.setBorder(BorderFactory.createLineBorder(Color.RED));
		JButton lanButton = new JButton("L�na");
		lanButton.setPreferredSize(new Dimension(200, 55));
		JButton bokaButton = new JButton("Boka");
		bokaButton.setPreferredSize(new Dimension(200, 55));

		leftUpperInnerContainer.add(lanButton);
		leftUpperInnerContainer.add(bokaButton);

		upperInnerContainer.add(leftUpperInnerContainer);

		JPanel rightUpperInnerContainer = new JPanel();
		rightUpperInnerContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JButton histButton = new JButton("Historik");
		histButton.setPreferredSize(new Dimension(200, 55));
		JButton lagerButton = new JButton("Lager");
		lagerButton.setPreferredSize(new Dimension(200, 55));

		rightUpperInnerContainer.add(histButton);
		rightUpperInnerContainer.add(lagerButton);

		upperInnerContainer.add(rightUpperInnerContainer);
		upperContainer.add(upperInnerContainer);

		JPanel lowerInnerContainer = new JPanel(new GridLayout(1, 2));

		JPanel leftLowerInnerContainer = new JPanel(new GridLayout(4, 1));
		leftLowerInnerContainer.setBorder(BorderFactory.createLineBorder(Color.blue));
		leftLowerInnerContainer.add(createButton("Ta Bort"));

		JPanel rightLowerInnerContainer = new JPanel(new GridLayout(4, 1));
		rightLowerInnerContainer.setBorder(BorderFactory.createLineBorder(new Color(130, 100, 10)));
		rightLowerInnerContainer.add(createButton("Registrera"));
		rightLowerInnerContainer.add(createButton("S�k"));

		lowerInnerContainer.add(leftLowerInnerContainer);
		lowerInnerContainer.add(rightLowerInnerContainer);

		upperContainer.setBorder(BorderFactory.createLineBorder(Color.orange));

		JPanel lowerContainer = new JPanel(new GridLayout(1, 2));

		lowerContainer.setBorder(BorderFactory.createLineBorder(Color.red));
		lowerContainer.add(lowerInnerContainer);

		this.add(upperContainer);
		this.add(lowerContainer);
		this.setResizable(false);
		pack();
		this.setVisible(true);
	}

	public JPanel createButton(String name) {
		JPanel temp = new JPanel();
		temp.setBorder(BorderFactory
				.createLineBorder(new Color(rand.nextInt(255) + 1, rand.nextInt(255) + 1, rand.nextInt(255) + 1)));
		JButton Button = new JButton(name);
		Button.setPreferredSize(new Dimension(200, 55));

		switch (name) {

		case "Ta Bort":
			Button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					Object[][] temp;

					String SQL = "select *from bok where Media_ID=" + txt.getText();

					temp = conn.getData(SQL);

					if (temp.length > 0) {
						SQL = "DELETE FROM bok where Media_ID=" + txt.getText();
						conn.sendData(SQL);
						SQL = "delete from media where ID=" + txt.getText();
						conn.sendData(SQL);
					} else {

						SQL = "select *from dvd where Media_ID=" + txt.getText();

						temp = conn.getData(SQL);
						if (temp.length > 0) {
							SQL = "DELETE FROM dvd where Media_ID=" + txt.getText();
							conn.sendData(SQL);
							SQL = "delete from media where ID=" + txt.getText();
							conn.sendData(SQL);
						} else {

							SQL = "select *from ljudbok where Media_ID=" + txt.getText();

							temp = conn.getData(SQL);
							if (temp.length > 0) {
								SQL = "DELETE FROM ljudbok where Media_ID=" + txt.getText();
								conn.sendData(SQL);
								
								SQL = "delete from media where ID=" + txt.getText();
								conn.sendData(SQL);
							}

						}
					}

				}

			});
			break;
			
		case "S�k":
			
			Button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					Object[][] temp;
					StringBuilder tempS= new StringBuilder();
					String SQL = "select *from bok where Media_ID=" + txt.getText();

					temp = conn.getData(SQL);

					if (temp.length > 0) {
						
						
						SQL = "select title from media where ID=" + txt.getText();
						temp = conn.getData(SQL);
						
						JOptionPane.showMessageDialog(null, "Bok title: "+temp[0][0]);
						
					} else {

						SQL = "select *from dvd where Media_ID=" + txt.getText();

						temp = conn.getData(SQL);
						if (temp.length > 0) {
							SQL = "DELETE FROM dvd where Media_ID=" + txt.getText();
							conn.sendData(SQL);
							SQL = "delete from media where ID=" + txt.getText();
							conn.sendData(SQL);
						} else {

							SQL = "select *from ljudbok where Media_ID=" + txt.getText();

							temp = conn.getData(SQL);
							if (temp.length > 0) {
								SQL = "DELETE FROM ljudbok where Media_ID=" + txt.getText();
								conn.sendData(SQL);
								
								SQL = "delete from media where ID=" + txt.getText();
								conn.sendData(SQL);
							}

						}
					}

				}

			});
			
			break;

		}

		temp.add(Button);
		return temp;
	}

}
