package main.java;

import net.pms.dlna.virtual.VirtualFolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Recurse KeySearch. */
public class KeyboardRequester extends VirtualFolder {

	/** Logger. */
	private static final Logger logger = LoggerFactory.getLogger(KeyboardRequester.class);

	/** Current request. */
	private String request = "";

	/** Options plugins. */
	private Options options;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            Name of folder.
	 * @param thumbnailIcon
	 *            Thumbnail icon.
	 * @param Options
	 *            Options.
	 * @param request
	 *            Request patern.
	 */
	public KeyboardRequester(String name, String thumbnailIcon, Options options, String request) {
		super(name, thumbnailIcon);
		if (request != null) {
			this.request = request + name;
		}
		this.options = options;

		addChild(new ResultFolder("Search..." + this.request, null, options, this.request));
		logger.trace("KeyboardRequester()" + request);
	}

	/** {@inheritDoc} */
	@Override
	public void discoverChildren() {
		logger.trace("discoverChildren()");
		logger.debug("includeFolder: " + options);
		addChild(new KeyboardRequester("[abc]", null, options, request));
		addChild(new KeyboardRequester("[def]", null, options, request));
		addChild(new KeyboardRequester("[ghi]", null, options, request));
		addChild(new KeyboardRequester("[klm]", null, options, request));
		addChild(new KeyboardRequester("[nop]", null, options, request));
		addChild(new KeyboardRequester("[qrs]", null, options, request));
		addChild(new KeyboardRequester("[tuv]", null, options, request));
		addChild(new KeyboardRequester("[wxyz]", null, options, request));
		addChild(new KeyboardRequester("[. _-]", null, options, request));
		addChild(new KeyboardRequester("[0-9]", null, options, request));
	}
}
