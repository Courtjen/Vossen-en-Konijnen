package vk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import vk.actor.*;
import vk.model.*;

public class TextView extends AbstractView{

	private static final long serialVersionUID = 1L;

	// A statistics object computing and storing simulation information
	private JTextArea output;

	/**
	 * Create a view of the given width and height.
	 */
	public TextView(Model newModel) {
		super(newModel);

		output = new JTextArea("Tekstview: \n\n", 40, 40);
		output.setEditable(false);
		output.setLineWrap(true);
		
		JScrollPane scrollBar = new JScrollPane(output);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollBar, BorderLayout.CENTER);
	}

	/**
	 * Paints the TextView
	 */
	
	@Override
	public void paintComponent(Graphics gInput) {
		gInput.setColor(Color.WHITE);
		gInput.fillRect(0, 0, this.getWidth(), this.getHeight());

		float rC=this.model.getCount(Rabbit.class);
		float fC=this.model.getCount(Fox.class);
		float bC=this.model.getCount(Bear.class);
		float hC=this.model.getCount(Hunter.class);
		int steps = this.model.getSteps();

		output.append("Step: " + steps + "\n");
		output.append("Rabbit: " + rC + "\n");
		output.append("Fox: " + fC + "\n");
		output.append("Bear: " + bC + "\n");
		output.append("Hunter: " + hC + "\n");
		output.append("-----------------------\n");

		output.setCaretPosition(this.output.getDocument().getLength());
	}


	/**
	 * Returns the output of the TextView
	 * @return the TextView
	 */
	public JTextArea getOutput() {
		return output;
	}

	@Override
	public void preparePaint() {
		// TODO Auto-generated method stub
	}
}