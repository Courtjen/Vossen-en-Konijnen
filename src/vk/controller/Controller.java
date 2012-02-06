package vk.controller;

import javax.swing.*;

import vk.model.Model;
import vk.simulator.Simulator;
import vk.view.Legenda;

import java.awt.GridLayout;
import java.awt.event.*;

/**
 *
 * @author Pim Vellinga
 * @version 0.0
 */

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener {

	private JButton btnStart1;
	private JButton btnStart100;
	private JTextField aantalStappen;
	private JButton start;
	private JButton stop;
	private JButton btnReset;

	public Controller(Model newModel) {
		super(newModel);

		this.add(makeMenuBar());
		this.add(makeWestBorder());
		this.add(makeEastBorder());
	}

	/**
	 * Makes a JPanel with a BoxLayout.
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
		
		final JTextField aantalStappen = new JTextField();
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
	 * Creates a JPanel with a BoxLayout to facilitate the Graphics
	 * @return
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
	 * Makes the menubar
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
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.btnStart1) {
			model.simulateOneStep();
		}

		if (e.getSource()==this.btnStart100) {
			model.simulate(100);
		}

		if (e.getSource()==this.start) {
			model.runApplication();
		}

		if (e.getSource()==this.stop) {
			this.model.stop();
		}

		if (e.getSource()==this.btnReset) {
			model.reset();
		}
		if (e.getActionCommand() == "Legenda"){
			Legenda lgd = new Legenda(new JFrame(), "Legenda");
		}
		if(e.getActionCommand() == "Afsluiten"){
			System.exit(0);
		}
		if(e.getActionCommand() == "Test"){
			System.out.println("Test gelukt!");
		}
		
		if(e.getActionCommand() == "Simuleer"){
			try{
				String aantalstappen = this.aantalStappen.getText();
				int aantal = Integer.parseInt(aantalstappen);

				if (aantal<=0)
					System.out.println("Aantal dagen mag geen 0 zijn!");
				else{
					if (model.run == true) model.stop();
					model.simulate(aantal);
				}
			}
			catch (Exception exc){
				System.out.println("Voer een positief getal in!");
			}
			if (!Simulator.run) Simulator.runApplication();
		}
		if (e.getActionCommand() == "Start"){
			if (!Simulator.run) Simulator.runApplication();
		}
		if(e.getActionCommand() == "Stop"){
			if (Simulator.run) Simulator.stop();
		}
		
		if(e.getActionCommand() == "Reset"){
			if (Simulator.run) Simulator.stop();
			Simulator.reset();
		}
	}
}
