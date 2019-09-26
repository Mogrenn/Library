package Library;

import javax.swing.JOptionPane;

import javaDB.javaDB;

public class SQL {
	
	private javaDB conn;
	
	public SQL() {
		
		conn = new javaDB("localhost","root","","bibliotek");
		
	}
	
	public Object[][] getData(String stmt){
		
		Object[][] temp = conn.getData(stmt);
		return temp;
	}
	
	
	public void sendData(String stmt) {
		
		try {
			conn.execute(stmt);
			JOptionPane.showMessageDialog(null, "nice");
		}catch(Exception error) {
			JOptionPane.showMessageDialog(null, error.getStackTrace());
		}
		
	}
	
}
