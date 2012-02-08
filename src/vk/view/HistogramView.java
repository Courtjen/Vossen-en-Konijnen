package vk.view;

import java.awt.Color;
import java.awt.Graphics;

import vk.actor.*;
import vk.model.*;

@SuppressWarnings("serial")
public class HistogramView extends AbstractView{

	private final int SCALE = 8;

	public HistogramView(Model newModel){
		super(newModel);
	}

	/**
	 * Creates a Histogram
	 */
	@Override
	public void paintComponent(Graphics gInput)
	{
		gInput.setColor(Color.WHITE);
		gInput.fillRect(0, 0, this.getWidth(), this.getHeight());

		int rH = (Math.round(model.getCount(Rabbit.class)))/this.SCALE;
		int fH = (Math.round(model.getCount(Fox.class)))/this.SCALE;
		int bH = (Math.round(model.getCount(Bear.class)))/this.SCALE;
		int hH = (Math.round(model.getCount(Hunter.class)))/this.SCALE;
		int gH = (Math.round(model.getCount(Grass.class)))/this.SCALE;


		gInput.setColor(this.model.getColor(Rabbit.class));
		gInput.fillRect(10, 10, 50, (rH));
		gInput.setColor(this.model.getColor(Fox.class));
		gInput.fillRect(70, 10, 50, (fH));
		gInput.setColor(this.model.getColor(Bear.class));
		gInput.fillRect(130, 10, 50, (bH));
		gInput.setColor(this.model.getColor(Hunter.class));
		gInput.fillRect(190, 10, 50, (hH));
		gInput.setColor(this.model.getColor(Grass.class));
		gInput.fillRect(250, 10, 50, (gH));
	}

	@Override
	public void preparePaint()
	{
		// TODO Auto-generated method stub
	}
}
