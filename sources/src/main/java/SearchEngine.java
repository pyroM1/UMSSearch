package main.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JComponent;

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
		return new Config(options);
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
			FileInputStream fin = new FileInputStream("optionsUMSSearch");
			ObjectInputStream ois = new ObjectInputStream(fin);
			loadedOption = (Options) ois.readObject();
			options.setIncludeFolder(loadedOption.isIncludeFolder());
			ois.close();
		} catch (Exception e) {
			logger.warn("Fail to load options. Use default options.", e);
		}
	}
}
