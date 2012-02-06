package vk.view;

import vk.model.Model;

/**
 *
 * @author Pim Vellinga
 * @version 1.0
 */

@SuppressWarnings("serial")
public class View extends AbstractView {

	AbstractView animatedView;

	public View(Model newModel) {
		super(newModel);
		this.animatedView = new AnimatedView(this.model);

		this.add(this.animatedView);
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
