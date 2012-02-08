package vk.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

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
	private Model model;
	private Controller controller;
	
	public FieldView fieldview;
	public HistogramView histoview;
	public PieView pieview;
	public TextView textview;
	public ControlView controlview;
	
	public JPanel eastborder;
	
	public JLabel population;
	public JLabel lblSteps;
	public JLabel lblVersion;
	
    public final String STEP_PREFIX = "Step: ";
    public final String POPULATION_PREFIX = "Population: ";
    public final String VERSION_PREFIX = "Version 1.0";
	
    
    /**
     * Makes a new Main object that initializes all the settings for the simulation.
     * 
     */
	public Main(){
		model = new Model(this);
			
		controller = new Controller(model);
		
		fieldview = new FieldView(model);
		histoview = new HistogramView(model);
		pieview  = new PieView(model);
		textview = new TextView(model);
		controlview = new ControlView(model);
		
		JTabbedPane tjp = new JTabbedPane();
		tjp.addTab("Cirkeldiagram", pieview);
		tjp.addTab("Histogram", histoview);
		tjp.addTab("Tekstview", textview);
		tjp.addTab("Instellingen", controlview);
		
		eastborder = new JPanel();
		eastborder.add(tjp);
		
		setTitle("Vossen en Konijnen");

		JPanel panel = new JPanel();
		
	    population = new JLabel(this.POPULATION_PREFIX, SwingConstants.CENTER);
	    lblSteps = new JLabel(this.STEP_PREFIX, SwingConstants.CENTER);
	    lblVersion = new JLabel(this.VERSION_PREFIX, SwingConstants.LEFT);

		
		Container content = getContentPane();

		content.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		
		setJMenuBar(controller.makeMenuBar());
		
		panel.add(lblSteps, BorderLayout.NORTH);
		panel.add(fieldview, BorderLayout.CENTER);
		panel.add(population, BorderLayout.SOUTH);
		
			
		content.add(eastborder, BorderLayout.EAST);
		content.add(controller.makeWestBorder(), BorderLayout.WEST);
		content.add(panel, BorderLayout.CENTER);
		content.add(lblVersion, BorderLayout.SOUTH);
		
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		model.reset();
		
		int[] location = centerFrame(this);
        this.setLocation(location[0], location[1]);
	}
	
	public static int[] centerFrame(JFrame frame){
		int[] place = new int[2];
		int frameHeight = frame.getHeight();
		int frameWidth = frame.getWidth();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = dim.height;
		int screenWidth = dim.width;

		place[0] = ((screenWidth-frameWidth)/2);
		place[1] = ((screenHeight-frameHeight)/2);

		return place;
	}

}
