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

	/** Constructor. */
	public KeyboardRequester(String name, String thumbnailIcon, String request) {
		super(name, thumbnailIcon);
		if (request != null) {
			this.request = request + name;
		}
		addChild(new ResultFolder("Search..." + this.request, null, this.request));
		logger.trace("KeyboardRequester()" + request);
	}

	@Override
	public void discoverChildren() {
		logger.trace("discoverChildren()");
		addChild(new KeyboardRequester("[abc]", null, request));
		addChild(new KeyboardRequester("[def]", null, request));
		addChild(new KeyboardRequester("[ghi]", null, request));
		addChild(new KeyboardRequester("[klm]", null, request));
		addChild(new KeyboardRequester("[nop]", null, request));
		addChild(new KeyboardRequester("[qrs]", null, request));
		addChild(new KeyboardRequester("[tuv]", null, request));
		addChild(new KeyboardRequester("[wxyz]", null, request));
		addChild(new KeyboardRequester("[. _-]", null, request));
		addChild(new KeyboardRequester("[0-9]", null, request));
	}
}
