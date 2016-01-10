package main.java;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.pms.dlna.DLNAResource;
import net.pms.external.AdditionalFolderAtRoot;

/** Entry Point Plugin. */
public class SearchEngine implements AdditionalFolderAtRoot {

	/** Root Element. */
	private KeyboardRequester kbRequest = new KeyboardRequester("Search", null, null);

	@Override
	public JComponent config() {
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(4, 1);
		panel.setLayout(gridLayout);
		panel.add(new Label("Nothing to configure."));
		panel.add(new Label("Thank you for use it !"));
		panel.add(new Label("Make by pyroM1"));
		panel.add(new Label("Sources can be found here: https://github.com/pyroM1/UMSSearch"));

		return panel;
	}

	/**  {@inheritDoc} */
	@Override
	public String name() {
		return "Search files";
	}

	/**  {@inheritDoc} */
	@Override
	public void shutdown() {
	} 

	/**  {@inheritDoc} */
	@Override
	public DLNAResource getChild() {
		return kbRequest;
	}
}
