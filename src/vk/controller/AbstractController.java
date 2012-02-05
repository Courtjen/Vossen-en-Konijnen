package vk.controller;

import javax.swing.*;

import vk.model.Model;

public abstract class AbstractController extends JPanel {

	private static final long serialVersionUID = 1L;

	protected Model model;

	public AbstractController(Model newModel) {
		this.model=newModel;
	}
}