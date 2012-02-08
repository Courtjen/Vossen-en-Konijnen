package vk.view;

import java.awt.Dimension;
import java.awt.Graphics;
import vk.model.*;

public class FieldView extends AbstractView
{
	private static final long serialVersionUID = 1L;

	/**
	 * Create a new FieldView component.
	 */
    public FieldView(Model model)
    {
     super(model);
    }

    
	/**
	 * The field view component needs to be redisplayed. Copy the
	 * internal image to screen.
	 */
	@Override
	public void paintComponent(Graphics g1)
	{
		if(this.fieldImage != null) {
			Dimension currentSize = getSize();
			if(size.equals(currentSize)) {
				g1.drawImage(fieldImage, 0, 0, null);
			}
			else {
				// Rescale the previous image.
				g1.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
			}
		}
	}
	
	/**
	 * Prepare for a new round of painting. Since the component
	 * may be resized, compute the scaling factor again.
	 */
	public void preparePaint()
	{
		if(!size.equals(getSize())) { // if the size has changed...
			size = getSize();
			fieldImage = createImage(size.width, size.height);
			g = fieldImage.getGraphics();

			xScale = size.width / gridWidth;
			if(xScale < 1) {
				xScale = GRID_VIEW_SCALING_FACTOR;
			}
				yScale = size.height / gridHeight;
			if(yScale < 1) {
				yScale = GRID_VIEW_SCALING_FACTOR;
			}
		}
	}

	/**
	 * Tell the GUI manager how big we would like to be.
	 */
	public Dimension getPreferredSize()
	{
		return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
				gridHeight * GRID_VIEW_SCALING_FACTOR);
	}

}