package vk.main;

import java.awt.BorderLayout;
import java.awt.Container;

import vk.model.*;
import vk.view.*;
import vk.controller.*;


import javax.swing.*;

public class Main extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FieldView view;
	private Model model;

	private Controller controller;
	
	
    public static JLabel population;
    
    //private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";

	public Main(){
		model = new Model(this);
		
		view = new FieldView(model);
		
		controller = new Controller(model);
				
		setTitle("Vossen en Konijnen");

		JPanel panel = new JPanel();

		population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
		
		Container content = getContentPane();

		content.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		
		setJMenuBar(controller.makeMenuBar());
		
		panel.add(controller.makeEastBorder(), BorderLayout.EAST);
		panel.add(controller.makeWestBorder(), BorderLayout.WEST);

		panel.add(view, BorderLayout.CENTER);
		panel.add(population, BorderLayout.SOUTH);
		content.add(panel,BorderLayout.SOUTH);
		setResizable(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		model.reset();


	}
	
}
