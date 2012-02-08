package vk.controller;

import javax.swing.*;

import vk.model.*;

public abstract class AbstractController extends JPanel {

	private static final long serialVersionUID = 1L;

	protected Model model;

	public AbstractController(Model newModel) {
		model = newModel;
	}
}