package vk.controller;

import javax.swing.*;

import vk.exception.RunException;
import vk.model.Model;
import vk.view.Legenda;

import java.awt.GridLayout;
import java.awt.event.*;

/**
 * The class Controller handles all the events and creates all the buttons related to the view
 *
 * @author Pim Vellinga
 * @version 1.0
 */

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener {

	private JTextField aantalStappen;
	private String steps;
	
	public static int toRun = 0;
	
	/**
	 * Creates a new controller with a reference to the model
	 * @param newModel
	 */
	public Controller(Model newModel) {
		super(newModel);

		this.add(makeMenuBar());
		this.add(makeWestBorder());
		this.add(makeEastBorder());
	}

	/**
	 * Creates the panel on the left, containing all the buttons used in the Simulator.
	 * Contains buttons
	 *
	 * @return JPanel
	 */
	public JPanel makeWestBorder() {
		JPanel panel2 = new JPanel();
		JPanel westborder = new JPanel();

		//Make buttons
		JButton btnStart1 = new JButton("Step 1");
		btnStart1.addActionListener(this);
		
		JButton btnStart100 = new JButton("Step 100");
		btnStart100.addActionListener(this);
		
		this.aantalStappen = new JTextField();
		steps = aantalStappen.getText();
		
		JButton btnSimuleer = new JButton("Simuleer");
		btnSimuleer.addActionListener(this);
		
		final JButton btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		
		final JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(this);
		
		final JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		

		//An empty label, to create an empty line in the buttonmenu
		JLabel emptyLabel1 = new JLabel();
		JLabel emptyLabel2 = new JLabel();
		JLabel emptyLabel3 = new JLabel();

		//Make frames
		panel2.add(btnStart1);
		panel2.add(btnStart100);

		panel2.add(emptyLabel1);
		panel2.add(aantalStappen);
		panel2.add(btnSimuleer);

		panel2.add(emptyLabel2);
		panel2.add(btnStart);
		panel2.add(btnStop);

		panel2.add(emptyLabel3);
		panel2.add(btnReset);

		panel2.setLayout(new GridLayout(0,1));


		westborder.add(panel2);
		westborder.setVisible(true);

		return westborder;
	}
	
	/**
	 * Creates the panel on the right, needed to display the Graphics
	 * 
	 * @return JPanel
	 */
	
	public JPanel makeEastBorder(){
		JPanel panel = new JPanel();
		JPanel eastborder =  new JPanel();
		
		final JButton btnTest = new JButton("Test");
		btnTest.addActionListener(this);
		
		panel.add(btnTest);
		panel.setLayout(new GridLayout(0,3));

		eastborder.add(panel);
		eastborder.setVisible(true);
		
		return eastborder;
	}
	
	/**
	 * Creates the menubar
	 *
	 * @return JMenuBar
	 */
	public JMenuBar makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();

		JMenu menuMenu1 = new JMenu("Bestand");
		final JMenuItem quitItem = new JMenuItem("Afsluiten");
		quitItem.addActionListener(this);
		menuMenu1.add(quitItem);
		menuBar.add(menuMenu1);
		
		JMenu menuHelp = new JMenu("Help");
		final JMenuItem legendaItem = new JMenuItem("Legenda");
		menuHelp.add(legendaItem);
		legendaItem.addActionListener(this);
		menuBar.add(menuHelp);

		return menuBar;
	}
	
	/**
	 * Handles the events for every action performed
	 * 
	 * @throws RunException when an error occurs
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		//--------- Knoppen
		
		if(e.getActionCommand() == "Step 1"){
			this.model.simulateOneStep();
		}
		if(e.getActionCommand() == "Step 100"){
			this.model.runApplication(100);
		}
		if(e.getActionCommand() == "Simuleer"){
			try{
				steps = aantalStappen.getText();
				System.out.println(steps);
				int aantal = Integer.parseInt(steps);
				
				toRun = aantal;

				if (aantal<=0)
					System.out.println("Aantal dagen mag geen 0 zijn!");
				else{
					if (model.run == true) model.stop();
					model.simulate(aantal);
				}
			}
			catch (Exception exc){
				exc.printStackTrace();
				System.out.println("Voer een positief getal in!");
			}
			if (!model.run)
				try {
					model.runApplication();
				} catch (RunException e1) {
					e1.printStackTrace();
				}
		}
		if (e.getActionCommand() == "Start"){
			if (! model.run)
				try {
					model.runApplication();
				} catch (RunException e1) {
					e1.printStackTrace();
				}
		}
		if (e.getActionCommand() == "Stop"){
			if (model.run) model.stop();
		}
		if (e.getActionCommand() == "Reset"){
			if (model.run) model.stop();
			model.reset();
		}
		
		if (e.getActionCommand() == "Test"){
			System.out.print("Test gelukt");
		}
		
		//--- Menu items
		
		if (e.getActionCommand() == "Legenda"){
			new Legenda(new JFrame(), "Legenda");
		}
		if(e.getActionCommand() == "Afsluiten"){
			System.exit(0);
		}
	}
}
