package main.java;

import java.io.Serializable;

/** Options plugin. */
public class Options implements Serializable {

	/** Serial UUID. */
	private static final long serialVersionUID = 8031583124288430452L;

	/** Include folder to search file. */
	private boolean includeFolder;

	/**
	 * Getter for includeFolder.
	 * 
	 * @return the value.
	 */
	public boolean isIncludeFolder() {
		return includeFolder;
	}

	/**
	 * Setter value for includeFolder
	 * 
	 * @param includeFolder
	 *            the includeFolder to set
	 */
	public void setIncludeFolder(boolean includeFolder) {
		this.includeFolder = includeFolder;
	}
}
