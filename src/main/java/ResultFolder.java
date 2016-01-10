package main.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pms.PMS;
import net.pms.dlna.RealFile;
import net.pms.dlna.virtual.VirtualFolder;
import net.pms.formats.Format;
import net.pms.formats.FormatFactory;

/** Folder inspector. */
public class ResultFolder extends VirtualFolder {

	/** Logger. */
	private static final Logger logger = LoggerFactory.getLogger(KeyboardRequester.class);

	/** Current request. */
	private String request = null;

	/** Constructor. */
	public ResultFolder(String name, String thumbnailIcon, String request) {
		super(name, thumbnailIcon);
		this.request = request;
	}

	/** {@inheritDoc} */
	@Override
	public void discoverChildren() {
		File[] tabFiles = PMS.get().getFoldersConf();
		List<File> lFiles = new ArrayList<File>();
		List<File> lFolders = new ArrayList<File>();

		lFolders.addAll(Arrays.asList(tabFiles));
		while (!lFolders.isEmpty()) {
			File folder = lFolders.remove(0);
			try {
				for (File current : folder.listFiles()) {
					if (current != null) {
						if (current.isDirectory()) {
							lFolders.add(current);
						} else {
							lFiles.add(current);
						}
					}
				}
			} catch (Exception ex) {
				logger.error("Fail to list folder: " + folder.getAbsoluteFile(), ex);
			}
		}

		for (File current : lFiles) {
			String name = current.getName().toLowerCase();
			if (name.matches(".*" + request + ".*")) {
				Format format = FormatFactory.getAssociatedFormat(name);
				if (format != null && format.isVideo()) {
					this.addChild(new RealFile(current));
				}
			}
		}
	}
}