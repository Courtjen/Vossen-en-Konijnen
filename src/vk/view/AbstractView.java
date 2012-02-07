package vk.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

import vk.model.AbstractModel;

public abstract class AbstractView extends JPanel {

	public Graphics g;
	public int xScale, yScale;

	protected int gridWidth, gridHeight;
    protected Dimension size;
    protected Image fieldImage;
    
    protected final int GRID_VIEW_SCALING_FACTOR = 6;
	
	private static final long serialVersionUID = 1L;

	protected AbstractModel model;

	public AbstractView(AbstractModel newModel) {
		model = newModel;
		newModel.addView(this);
		
		size = new Dimension();
		gridWidth = 100;
		gridHeight = 100;
	}

	public AbstractModel getModel() {
		return model;
	}

	public void updateView(){
		repaint();
	}

	public void preparePaint() {
		// TODO Auto-generated method stub
		
	}
}
