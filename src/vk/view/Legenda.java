package vk.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Legenda extends JDialog implements ActionListener {
		
	public Legenda(JFrame parent, String title) {
		super(parent, title, true);
		if (parent != null) {
			Dimension parentSize = parent.getSize(); 
			Point p = parent.getLocation(); 
			setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
		}
		this.setBounds(0,0, 200, 200);
		
		JTextArea textarea = new JTextArea("De volgende kleuren staan voor de volgende dingen: \n\n" +
										"Blauw: Vos. \n" +
										"Rood: Beer. \n" +
										"Oranje: Konijn. \n" + 
										"Zwart: Jager.");
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		
		textarea.setEditable(false);
		
		getContentPane().add(textarea);
		JPanel buttonPane = new JPanel();
		JButton button = new JButton("OK"); 
		buttonPane.add(button); 
		button.addActionListener(this);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		parent.pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		setVisible(false); 
		dispose(); 
	}
}