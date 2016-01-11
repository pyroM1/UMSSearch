package main.java;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config extends JPanel {

	/** Serial UUID. */
	private static final long serialVersionUID = -1276686281065209870L;

	/** Logger. */
	private static final Logger logger = LoggerFactory.getLogger(Config.class);

	/**
	 * Create the panel.
	 */
	public Config(final Options options) {
		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox);

		JLabel lblIncludePath = new JLabel("Matching folder path  ");
		horizontalBox.add(lblIncludePath);

		final JCheckBox checkBox = new JCheckBox();
		checkBox.setSelected(options.isIncludeFolder());
		horizontalBox.add(checkBox);

		checkBox.addItemListener(new ItemListener() {
			/** {@inheritDoc} */
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				logger.trace("itemStateChanged()");
				options.setIncludeFolder(checkBox.isSelected());
			}
		});
		checkBox.setFont(new Font("Dialog", Font.BOLD, 12));

		JLabel lbl3 = new JLabel("Make by pyroM1.");
		lbl3.setVerticalAlignment(SwingConstants.TOP);
		verticalBox.add(lbl3);

		JLabel lbl4 = new JLabel("Sources can be found here: https://github.com/pyroM1/UMSSearch");
		lbl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/pyroM1/UMSSearch"));
				} catch (IOException | URISyntaxException ex) {
					logger.error("Fail to open link.", ex);
				}
			}
		});
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		verticalBox.add(lbl4);

	}
}
