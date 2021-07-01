package games;
import javax.swing.*;

import games.Display;


public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame object = new JFrame();  
		//JButton but = new JButton();
		
		Game gameplay = new Game();
		object.setBounds(10, 10, 700, 600);
		object.setTitle("DX Ball");
		object.setResizable(false);
		object.setVisible(true);
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.add(gameplay);
		// graphics
	}

}
