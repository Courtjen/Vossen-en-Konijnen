package vk.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vk.model.Model;

/**
 *
 * @author Pim Vellinga
 * @version 1.0
 */

@SuppressWarnings("serial")
public class View extends AbstractView implements ActionListener {

	AbstractView animatedView;

	public View(Model newModel) {
		super(newModel);
		this.animatedView = new AnimatedView(this.model);

		this.add(this.animatedView);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Object command = e.getActionCommand();

		if (command=="Afsluiten"){
			System.exit(0);
		}
		else{
			System.out.println("Geen actie voor command bekend!\n Command = \""+command+"\";\n "+e+"\n");
		}
	}

	public void setModel(Model newModel) {
		this.model=newModel;
	}

	@Override
	public void updateView() {
		repaint();
	}
}
