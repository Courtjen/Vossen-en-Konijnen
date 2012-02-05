package vk.view;

import java.awt.Color;

import vk.main.RunException;
import vk.simulator.Field;

public interface SimulatorView {
	@SuppressWarnings("rawtypes")
	void setColor(Class cl, Color color);
	boolean isViable(Field field);
	void showStatus(int step, Field field) throws RunException;
}