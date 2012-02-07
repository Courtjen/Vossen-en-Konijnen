package vk.main;

import java.awt.BorderLayout;
import java.awt.Container;

import vk.model.*;
import vk.view.*;
import vk.controller.*;


import javax.swing.*;


/**
 * Main class that initializes the application
 * 
 * @author Pim
 * @version 1.0
 * 
 */
public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private FieldView view;
	private Model model;

	private Controller controller;
	
	public JLabel population;
	public JLabel lblSteps;
	public JLabel lblVersion;
	
    public final String STEP_PREFIX = "Step: ";
    public final String POPULATION_PREFIX = "Population: ";
    public final String VERSION_PREFIX = "Version 0.0";
	
    
    /**
     * Makes a new Main object that initializes all the settings for the simulation.
     * 
     */
	public Main(){
		model = new Model(this);
		
		view = new FieldView(model);
		
		controller = new Controller(model);
				
		setTitle("Vossen en Konijnen");

		JPanel panel = new JPanel();
		
	    population = new JLabel(this.POPULATION_PREFIX, SwingConstants.CENTER);
	    lblSteps = new JLabel(this.STEP_PREFIX, SwingConstants.CENTER);

		
		Container content = getContentPane();

		content.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		
		setJMenuBar(controller.makeMenuBar());
		
		panel.add(controller.makeEastBorder(), BorderLayout.EAST);
		panel.add(controller.makeWestBorder(), BorderLayout.WEST);

		panel.add(lblSteps, BorderLayout.NORTH);
		panel.add(view, BorderLayout.CENTER);
		panel.add(population, BorderLayout.SOUTH);
		content.add(panel,BorderLayout.SOUTH);
		
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		model.reset();		
	}
	
}
