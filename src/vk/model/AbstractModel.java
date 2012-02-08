package vk.model;

import java.util.*;

import vk.view.AbstractView;

public abstract class AbstractModel {

	protected List<AbstractView> views;

	public AbstractModel() {
		views = new ArrayList<AbstractView>();
	}

	public void addView(AbstractView view) {
		views.add(view);
	}

	protected void notifyViews() {
		for(AbstractView v: this.views) v.updateView();
	}

}
