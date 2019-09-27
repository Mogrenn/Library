package Library;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class storage extends JFrame{
	
	public storage(JFrame parent) {
		
		this.setPreferredSize(new Dimension(600,600));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.setLayout(new BorderLayout());
		pack();
		this.setVisible(true);
	}
	
	public JPanel createTable() {
		JPanel temp = new JPanel();
		
		
		
		return temp;
	}
	

}
