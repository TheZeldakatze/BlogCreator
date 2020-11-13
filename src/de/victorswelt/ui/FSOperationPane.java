package de.victorswelt.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.victorswelt.DatabaseManager;

public class FSOperationPane extends JPanel {
	JButton save, build;
	
	public FSOperationPane() {
		
		// set the layout
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// create the buttons
		save = new JButton("Save");
		build = new JButton("Build Pages");
		
		// add them to the panel
		add(save);
		add(build);
		
		// add the listeners
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseManager.getInstance().save();
			}
		});
	}
}
