package Library;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class lana extends JFrame {

	private SQL conn = new SQL();
	private String[] temp = new String[30];
	private JComboBox<String> larare;
	private JComboBox<String> elev;
	private final static String password = "Bra123";

	public lana(JFrame parent) {
		this.setPreferredSize(new Dimension(600, 600));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.setLayout(new GridLayout(2, 1));

		String stmt = "Select concat(larare.fnamn, ' ', larare.enamn) as namn from larare;";
		Object[][] temp = conn.getData(stmt);

		for (int i = 0; i < temp.length; i++)
			System.out.println(temp[i][0]);
		for (int i = 0; i < temp.length; i++) {

			this.temp[i] = temp[i][0].toString();

		}

		larare = new JComboBox<>(this.temp);

		stmt = "Select concat(elev.fnamn, ' ', elev.enamn) as namn from elev";
		temp = conn.getData(stmt);

		for (int i = 0; i < temp.length; i++) {

			this.temp[i] = (String) temp[i][0];

		}

		elev = new JComboBox<>(this.temp);

		JTextField txt = new JTextField("ID", 15);

		JButton send = new JButton("Skicka");

		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String stmt = "select *from utlanad where media_ID=" + txt.getText();
				Object[][] temp = conn.getData(stmt);

				if (temp.length < 0) {
					String psw = JOptionPane.showInputDialog("Skriv in lösenord");
					if (psw.equals(password)) {

						int lararID = larare.getSelectedIndex() + 1;
						int elevID = elev.getSelectedIndex() + 1;
						String mediaID = txt.getText();

						stmt = "insert into utlanad(larar_ID, elev_ID,media_ID,datum) VALUES(" + lararID + "," + elevID
								+ ",\"" + mediaID + " \", CURDATE());";
						conn.sendData(stmt);
					} else {
						JOptionPane.showMessageDialog(null, "Prova igen");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Redan Bokad");
				}
			}

		});

		this.add(larare);
		this.add(elev);
		this.add(txt);
		this.add(send);

		pack();
		this.setVisible(true);
	}

}
