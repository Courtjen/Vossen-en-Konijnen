package vk.view;

import vk.model.Model;

/**
 * A class to provide basic commands regarding Views.
 *
 * @author Pim Vellinga
 * @version 1.0
 */

@SuppressWarnings("serial")
public class View extends AbstractView {

	public View(Model newModel) {
		super(newModel);

		this.setVisible(true);
	}

	public void setModel(Model newModel) {
		this.model=newModel;
	}

	@Override
	public void updateView() {
		repaint();
	}
}
