package main.java;

import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.pms.dlna.DLNAResource;
import net.pms.external.AdditionalFolderAtRoot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Entry Point Plugin. */
public class SearchEngine implements AdditionalFolderAtRoot {

	/** Logger. */
	private static final Logger logger = LoggerFactory.getLogger(SearchEngine.class);

	/** Include folder matching. */
	private Options options = new Options();

	/** Root Element. */
	private KeyboardRequester kbRequest = new KeyboardRequester("Search", null, options, null);

	/** {@inheritDoc} */
	@Override
	public JComponent config() {
		loadOptions();

		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(5, 1);
		panel.setLayout(gridLayout);

		final Checkbox checkbox = new Checkbox("Search in folder path: ", options.isIncludeFolder());
		checkbox.addItemListener(new ItemListener() {

			/** {@inheritDoc} */
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				logger.warn("itemStateChanged()");
				options.setIncludeFolder(checkbox.getState());
			}
		});
		panel.add(checkbox);

		panel.add(new Label("Nothing else to configure."));
		panel.add(new Label("Thank you for use it !"));
		panel.add(new Label("Make by pyroM1"));
		panel.add(new Label("Sources can be found here: https://github.com/pyroM1/UMSSearch"));

		return panel;
	}

	/** {@inheritDoc} */
	@Override
	public String name() {
		return "Search files";
	}

	/** {@inheritDoc} */
	@Override
	public void shutdown() {
		try {
			FileOutputStream fout = new FileOutputStream("optionsUMSSearch");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(options);
			oos.close();
		} catch (Exception e) {
			logger.error("Fail to save options.");
		}
	}

	/** {@inheritDoc} */
	@Override
	public DLNAResource getChild() {
		return kbRequest;
	}

	/** Load options. */
	private void loadOptions() {
		try {
			Options loadedOption;
			FileInputStream fin = new FileInputStream("options");
			ObjectInputStream ois = new ObjectInputStream(fin);
			loadedOption = (Options) ois.readObject();
			options.setIncludeFolder(loadedOption.isIncludeFolder());
			ois.close();
		} catch (Exception e) {
			logger.debug("Fail to load options. Use default options.");
		}
	}
}
