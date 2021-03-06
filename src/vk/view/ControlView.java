package vk.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import vk.model.Model;

@SuppressWarnings("serial")

/**
 * This class provides a window with slidebuttons.
 * These can be used to change the settings for the game.
 * @author Pim
 *
 */
public class ControlView extends AbstractView implements ActionListener{

	/**
	 * Creates a new constructor and ads the model to the model list.
	 * @param newModel
	 */
	public ControlView(Model newModel) {
		super(newModel);

		setLayout(new GridLayout(0,2));

		JPanel panel = new JPanel();
		JButton btnReset = new JButton("Reset");
		panel.add(btnReset);

		add(makeRabbitPanel());
		add(makeFoxPanel());
		add(makeBearPanel());
		add(makeHunterPanel());
		add(makeGrassPanel());
		add(panel);

		setVisible(true);
	}
	
	/**
	 * Creates the panel for all the settings regarding Rabbits
	 * @return JPanel with the settings for the Rabbits
	 */

	public static JPanel makeRabbitPanel(){

		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titledborder = BorderFactory.createTitledBorder(loweredetched, "Rabbit");
		titledborder.setTitleJustification(TitledBorder.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));

		panel.add(makeAnimalPanel());

		panel.setBorder(titledborder);

		return panel;
	}
	
	/**
	 * Creates the panel for all the settings regarding Foxes
	 * @return JPanel with all the settings for the Foxes
	 */

	private static JPanel makeFoxPanel() {

		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titledborder = BorderFactory.createTitledBorder(loweredetched, "Fox");
		titledborder.setTitleJustification(TitledBorder.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));

		panel.add(makeAnimalPanel());

		panel.setBorder(titledborder);

		return panel;
	}
	
	/**
	 * Creates the panel for all the settings regarding Bears
	 * @return JPanel with all the settings for the Bears
	 */

	private static JPanel makeBearPanel() {

		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titledborder = BorderFactory.createTitledBorder(loweredetched, "Bear");
		titledborder.setTitleJustification(TitledBorder.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));

		panel.add(makeAnimalPanel());

		panel.setBorder(titledborder);

		return panel;
	}

	/**
	 * Creates the panel for all the settings regarding Hunters
	 * @return JPanel with all the settings for the Hunters
	 */
	
	private static JPanel makeHunterPanel() {

		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titledborder = BorderFactory.createTitledBorder(loweredetched, "Hunter");
		titledborder.setTitleJustification(TitledBorder.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));

		panel.add(makeAnimalPanel());

		panel.setBorder(titledborder);

		return panel;
	}

	/**
	 * Creates the panel for all the settings regardig Grass
	 * @return JPanel with all the settings for Grass
	 */
	private static JPanel makeGrassPanel() {

		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titledborder = BorderFactory.createTitledBorder(loweredetched, "Grass");
		titledborder.setTitleJustification(TitledBorder.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));

		panel.add(makeAnimalPanel());

		panel.setBorder(titledborder);

		return panel;
	}
	
	/**
	 * Creates the panel for all the settings regarding Animals
	 * @return JPanel with all the settings regarding Animals
	 */

	public static JPanel makeAnimalPanel(){

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));

		JLabel lblBreedingAge = new JLabel("Breeding age:");
		JSlider slBreedingAge = new JSlider(1, 10, 5);
		slBreedingAge.setMajorTickSpacing(1);
		slBreedingAge.setMinorTickSpacing(1);
		slBreedingAge.setPaintTicks(true);
		slBreedingAge.setPaintLabels(true);

		JLabel lblMaxAge = new JLabel("Max age:");
		JSlider slMaxAge = new JSlider(30, 50, 40);
		slMaxAge.setMajorTickSpacing(5);
		slMaxAge.setMinorTickSpacing(1);
		slMaxAge.setPaintTicks(true);
		slMaxAge.setPaintLabels(true);

		JLabel lblBreedingProbability = new JLabel("Breeding probability:");
		JSlider slBreedingProbability = new JSlider(1, 10, 5);
		slBreedingProbability.setMajorTickSpacing(1);
		slBreedingProbability.setMinorTickSpacing(1);
		slBreedingProbability.setPaintTicks(true);
		slBreedingProbability.setPaintLabels(true);

		JLabel lblMaxLitterSize = new JLabel("Max litter size:");
		JSlider slMaxLitterSize = new JSlider(1, 10, 3);
		slMaxLitterSize.setMajorTickSpacing(1);
		slMaxLitterSize.setMinorTickSpacing(1);
		slMaxLitterSize.setPaintTicks(true);
		slMaxLitterSize.setPaintLabels(true);

		JLabel lblFoodValue = new JLabel("Food value:");
		JSlider slFoodValue = new JSlider(1, 20, 8);
		slFoodValue.setMajorTickSpacing(2);
		slFoodValue.setMinorTickSpacing(1);
		slFoodValue.setPaintTicks(true);
		slFoodValue.setPaintLabels(true);

		panel.add(lblBreedingAge);
		panel.add(slBreedingAge);
		panel.add(lblMaxAge);
		panel.add(slMaxAge);
		panel.add(lblBreedingProbability);
		panel.add(slBreedingProbability);
		panel.add(lblMaxLitterSize);
		panel.add(slMaxLitterSize);
		panel.add(lblFoodValue);
		panel.add(slFoodValue);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preparePaint() {
		// TODO Auto-generated method stub
	}
}
