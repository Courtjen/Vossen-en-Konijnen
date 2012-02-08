package vk.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

import vk.model.Model;

public abstract class AbstractView extends JPanel {

	protected Model model;
	public Graphics g;
	public int xScale, yScale;

	protected int gridWidth, gridHeight;
    protected final int GRID_VIEW_SCALING_FACTOR = 6;
    
    protected Dimension size;
    protected Image fieldImage;
    
	
	private static final long serialVersionUID = 1L;

	public AbstractView(Model newModel) {
		model = newModel;
		model.addView(this);
		
		size = new Dimension();
		gridWidth = 125;
		gridHeight = 125;
	}

	public Model getModel() {
		return model;
	}

	public void updateView(){
		repaint();
	}

	public void preparePaint() {
	}
}
