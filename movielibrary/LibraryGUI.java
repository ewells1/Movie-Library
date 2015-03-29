package movielibrary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

public class LibraryGUI {

	JFrame frame;
	MovieLibrary lib = new MovieLibrary();
	private JTextArea text = new JTextArea();
	
	public LibraryGUI() {
		ObjectInputStream in;
		try {
			File file = new File("lib.ser");
			if(file.exists()) {
				in = new ObjectInputStream(new FileInputStream("lib.ser"));
				lib = (MovieLibrary)in.readObject();
				in.close();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame,
					"Unable to read from input file \n" +
							e1.getClass().getSimpleName() + ": " + e1.getMessage(),
							"Error",
							JOptionPane.WARNING_MESSAGE);
		}
		
		text.setText(lib.displayTitles());
		
		createAndShowGUI();
	}
	
	/**
	 * Main method for setting up and running GUI.
	 */
	private void createAndShowGUI(){
		frame = new JFrame("Movie Library");
		frame.setSize(600, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(new ExitAdapter());
		
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		bar.add(createMenu());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("List Movies", null, getListPanel(), "List Movies");
		tabbedPane.addChangeListener(e -> {
			text.setText(lib.displayTitles());
		});
		
		tabbedPane.addTab("Add Movie", null, getAddPanel(), "Add Movie");
		tabbedPane.addTab("Look Up Movie", null, getMoviePanel(), "Look Up Movie");
		tabbedPane.addTab("Look Up Disc", null, getDiscPanel(), "Look Up Disc");
		
		frame.add(tabbedPane);
	}
	
	/**
	 * Creates panel for List Movies tab.
	 * @return panel
	 */
	private JComponent getListPanel(){
		JPanel panel = new JPanel();
		Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Movies in Library");
		panel.setBorder(border);
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(text));
		return panel;
	}
	
	/**
	 * Creates panel for Add Movie panel.
	 * @return
	 */
	private JComponent getAddPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Set Title");
		top.setBorder(border);
		
		top.add(new JLabel("Title "));
		JTextField titleField = new JTextField(45);
		top.add(titleField);
		
		panel.add(top, BorderLayout.PAGE_START);
		
		JPanel middle = new JPanel();
		Border border1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Set Discs");
		middle.setBorder(border1);
		
		middle.add(new JLabel("Disc 1 ID "));
		JTextField discField1 = new JTextField(30);
		middle.add(discField1);
		
		JRadioButtonMenuItem dvd1 = new JRadioButtonMenuItem("DVD");
		JRadioButtonMenuItem bluray1 = new JRadioButtonMenuItem("Blu-Ray");
		middle.add(dvd1);
		middle.add(bluray1);
		ButtonGroup group1 = new ButtonGroup();
		group1.add(dvd1);
		group1.add(bluray1);
		
		middle.add(new JLabel("Disc 2 ID "));
		JTextField discField2 = new JTextField(30);
		middle.add(discField2);
		
		JRadioButtonMenuItem dvd2 = new JRadioButtonMenuItem("DVD");
		JRadioButtonMenuItem bluray2 = new JRadioButtonMenuItem("Blu-Ray");
		middle.add(dvd2);
		middle.add(bluray2);
		ButtonGroup group2 = new ButtonGroup();
		group2.add(dvd2);
		group2.add(bluray2);
		
		middle.add(new JLabel("Disc 3 ID "));
		JTextField discField3 = new JTextField(30);
		middle.add(discField3);
		
		JRadioButtonMenuItem dvd3 = new JRadioButtonMenuItem("DVD");
		JRadioButtonMenuItem bluray3 = new JRadioButtonMenuItem("Blu-Ray");
		middle.add(dvd3);
		middle.add(bluray3);
		ButtonGroup group3 = new ButtonGroup();
		group3.add(dvd3);
		group3.add(bluray3);
		
		middle.add(new JLabel("Disc 4 ID "));
		JTextField discField4 = new JTextField(30);
		middle.add(discField4);
		
		JRadioButtonMenuItem dvd4 = new JRadioButtonMenuItem("DVD");
		JRadioButtonMenuItem bluray4 = new JRadioButtonMenuItem("Blu-Ray");
		middle.add(dvd4);
		middle.add(bluray4);
		ButtonGroup group4 = new ButtonGroup();
		group4.add(dvd4);
		group4.add(bluray4);
		
		panel.add(middle);
		
		JTextField[] fields = {discField1, discField2, discField3, discField4};
		JRadioButtonMenuItem[] dvdButtons = {dvd1, dvd2, dvd3, dvd4};
		JRadioButtonMenuItem[] blurayButtons = {bluray1, bluray2, bluray3, bluray4};
		
		JPanel addClearPanel = new JPanel();
		addClearPanel.setLayout(new GridLayout(0,2));
		
		JButton add = new JButton("Add Movie");
		add.addActionListener(e -> {
			if (titleField.getText().length() > 0){
				lib.addMovie(titleField.getText());
				for (int i = 0; i < fields.length; i++){
					if (fields[i].getText().length() > 0){
						if (dvdButtons[i].isSelected()){
							lib.addDiscToMovie(titleField.getText(), fields[i].getText(), MovieVersion.DVD);
							JOptionPane.showMessageDialog(frame, titleField.getText() + " has been added to the library.");
							titleField.setText("");
							for (JTextField field : fields){
								field.setText("");
							}
							for (ButtonGroup group : new ButtonGroup[] {group1, group2, group3, group4}){
								group.clearSelection();
							}
						} else if (blurayButtons[i].isSelected()){
							lib.addDiscToMovie(titleField.getText(), fields[i].getText(), MovieVersion.BLURAY);
							JOptionPane.showMessageDialog(frame, titleField.getText() + " has been added to the library.");
							titleField.setText("");
							for (JTextField field : fields){
								field.setText("");
							}
							for (ButtonGroup group : new ButtonGroup[] {group1, group2, group3, group4}){
								group.clearSelection();
							}
						} else {
							JOptionPane.showMessageDialog(frame, "You must select a movie version for Disc " + (i + 1) + ".");
							lib.removeMovie(titleField.getText());
						}
					}
				}
			}
		});
		addClearPanel.add(add);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(e -> {
			titleField.setText("");
			for (JTextField field : fields){
				field.setText("");
			}
			for (ButtonGroup group : new ButtonGroup[] {group1, group2, group3, group4}){
				group.clearSelection();
			}
		});
		addClearPanel.add(clear);
		
		panel.add(addClearPanel, BorderLayout.PAGE_END);
		
		return panel;
	}
	
	private JComponent getMoviePanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel id = new JPanel();
		Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Search Movies");
		id.setBorder(border);
		id.add(new JLabel("Title or ID Number: "));
		JTextField idField = new JTextField(20);
		id.add(idField);
		
		JTextArea info = new JTextArea();
		JTextField titleField = new JTextField(40);
		JTextField originalTitle = new JTextField();
		
		JButton idSubmit = new JButton("Search");
		idSubmit.addActionListener(e -> {
			if (lib.getMovie(idField.getText()) != null){
				info.setText(lib.getMovie(idField.getText()).displayDiscs());
				titleField.setText(lib.getMovie(idField.getText()).getTitle());
				originalTitle.setText(lib.getMovie(idField.getText()).getTitle());
			} else {
				JOptionPane.showMessageDialog(frame, "Movie not found in library.");
			}
		});
		id.add(idSubmit);
		
		id.add(new JLabel(" or "));
		
		JButton browse = new JButton("Browse");
		browse.addActionListener(e -> {
			String movie = (String) JOptionPane.showInputDialog(frame, "Choose a movie:", "Input", 
					JOptionPane.QUESTION_MESSAGE, null, lib.getMovieTitles(), "DVD");
			if (movie != null){
				idField.setText(movie);
				info.setText(lib.getMovie(movie).displayDiscs());
				titleField.setText(movie);
				originalTitle.setText(movie);
			}
		});
		id.add(browse);
		panel.add(id, BorderLayout.PAGE_START);
		
		JPanel middle = new JPanel();
		Border border1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Movie Information");
		middle.setBorder(border1);
		middle.setLayout(new BorderLayout());
		middle.add(new JScrollPane(info));
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(new JLabel("Title: "));
		titlePanel.add(titleField);
		JButton update = new JButton("Update");
		update.addActionListener(e -> {
			if (titleField.getText().length() > 0){
				String newTitle = titleField.getText();
				lib.getMovie(originalTitle.getText()).editTitle(newTitle);
				originalTitle.setText(newTitle);
				idField.setText(newTitle);
			} else {
				JOptionPane.showMessageDialog(frame, "You must select a movie first.");
			}
		});
		titlePanel.add(update);
		middle.add(titlePanel, BorderLayout.PAGE_START);
		panel.add(middle);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(0,3));
		
		JButton addDisc = new JButton("Add Disc");
		addDisc.addActionListener(e -> {
			if (titleField.getText().length() > 0){
				String idNumber = (String) JOptionPane.showInputDialog(frame, "Enter disc ID number:");
				String version = (String) JOptionPane.showInputDialog(frame, "Choose a movie version:", "Input", 
					JOptionPane.QUESTION_MESSAGE, null, new String[] {"DVD", "Blu-Ray"}, "DVD");
				if (idNumber != null && version != null && version.equals("DVD")){
					lib.addDiscToMovie(titleField.getText(), idNumber, MovieVersion.DVD);
				} else if (idNumber != null && version != null && version.equals("Blu-Ray")) {
					lib.addDiscToMovie(titleField.getText(), idNumber, MovieVersion.BLURAY);
				}
				info.setText(lib.getMovie(idField.getText()).displayDiscs());
			} else {
				JOptionPane.showMessageDialog(frame, "You must select a movie first.");
			}
		});
		bottom.add(addDisc);
		
		JButton deleteDisc = new JButton("Delete Disc");
		deleteDisc.addActionListener(e -> {
			if (lib.getMovie(idField.getText()) != null){
				if (lib.getMovie(idField.getText()).getDiscs().size() > 0){
					String disc = (String) JOptionPane.showInputDialog(frame, "Choose a disc:", "Input", 
							JOptionPane.QUESTION_MESSAGE, null, lib.getMovie(idField.getText()).getDiscIDs(), 
							lib.getMovie(idField.getText()).getDiscIDs()[0]);
					if (disc != null){
						lib.getMovie(disc).removeDisc(disc);
						info.setText(lib.getMovie(idField.getText()).displayDiscs());
					}
				} else {
					JOptionPane.showMessageDialog(frame, "This movie has no discs to delete.");
				}
			} else {
				JOptionPane.showMessageDialog(frame, "You must select a movie first.");
			}
		});
		bottom.add(deleteDisc);
		
		JButton delete = new JButton("Delete Movie");
		delete.addActionListener(e -> {
			if (titleField.getText().length() > 0){
				int decision = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to delete this movie?","Confirmation", 
						JOptionPane.YES_NO_OPTION);
				if (decision == JOptionPane.YES_OPTION) {
					lib.removeMovie(titleField.getText());
					idField.setText("");
					titleField.setText("");
					info.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(frame, "You must select a movie first.");
			}
		});
		bottom.add(delete);
		
		panel.add(bottom, BorderLayout.PAGE_END);
		return panel;
	}
	
	private JComponent getDiscPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel id = new JPanel();
		Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Search Discs");
		id.setBorder(border);
		id.add(new JLabel("Title or ID Number: "));
		JTextField idField = new JTextField(20);
		id.add(idField);
		
		JTextField idNumField = new JTextField(35);
		JTextField originalID = new JTextField();
		JTextArea info = new JTextArea();
		
		JButton idSubmit = new JButton("Search");
		idSubmit.addActionListener(e -> {
			if (idField.getText() != null && lib.getDisc(idField.getText()) != null){
				originalID.setText(idField.getText());
				idNumField.setText(idField.getText());
				info.setText("Movie: " + lib.getMovie(idField.getText()).getTitle() + "\n" + lib.getDisc(idField.getText()));
			} else if (idField.getText() != null && lib.getMovie(idField.getText()) != null){
				if (lib.getMovie(idField.getText()).getDiscIDs().length > 1){
					String disc = (String) JOptionPane.showInputDialog(frame, "Choose a disc:", "Input", 
							JOptionPane.QUESTION_MESSAGE, null, lib.getMovie(idField.getText()).getDiscIDs(), 
							lib.getMovie(idField.getText()).getDiscIDs()[0]);
					originalID.setText(disc);
					idNumField.setText(disc);
					info.setText("Movie: " + idField.getText() + "\n" + lib.getDisc(disc));
				} else if (lib.getMovie(idField.getText()).getDiscIDs().length == 1){
					String disc = lib.getMovie(idField.getText()).getDiscIDs()[0];
					originalID.setText(disc);
					idNumField.setText(disc);
					info.setText("Movie: " + idField.getText() + "\n" + lib.getDisc(disc));
				} else {
					JOptionPane.showMessageDialog(frame, "Movie does not have any discs.");
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Movie not found in library.");
			}
		});
		id.add(idSubmit);
		
		id.add(new JLabel(" or "));
		
		JButton browse = new JButton("Browse");
		browse.addActionListener(e -> {
			String movie = (String) JOptionPane.showInputDialog(frame, "Choose a movie:", "Input", 
					JOptionPane.QUESTION_MESSAGE, null, lib.getMovieTitles(), "DVD");
			if (movie != null && lib.getMovie(movie).getDiscIDs().length > 0) {
				String disc;
				if (lib.getMovie(movie).getDiscIDs().length > 1){
					disc = (String) JOptionPane.showInputDialog(frame, "Choose a disc:", "Input", 
							JOptionPane.QUESTION_MESSAGE, null, lib.getMovie(movie).getDiscIDs(), 
							lib.getMovie(movie).getDiscIDs()[0]);
				} else {
					disc = lib.getMovie(movie).getDiscIDs()[0];
				}
				idField.setText(disc);
				idNumField.setText(disc);
				originalID.setText(disc);
				info.setText("Movie: " + movie + "\n" + lib.getDisc(disc));
			} else if (movie != null){
				JOptionPane.showMessageDialog(frame, "Movie does not have any discs.");
			}
		});
		id.add(browse);
		panel.add(id, BorderLayout.PAGE_START);
		
		JPanel middle = new JPanel();
		Border border1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Disc Information");
		middle.setBorder(border1);
		middle.setLayout(new BorderLayout());
		middle.add(new JScrollPane(info));
		
		JPanel idNumPanel = new JPanel();
		idNumPanel.add(new JLabel("Id Number: "));
		idNumPanel.add(idNumField);
		JButton update = new JButton("Update");
		update.addActionListener(e -> {
			if (idNumField.getText().length() > 0){
				String newId = idNumField.getText();
				lib.getDisc(originalID.getText()).editNumber(newId);
				originalID.setText(newId);
				idField.setText(newId);
				info.setText("Movie: " + lib.getMovie(newId).getTitle() + "\n" + lib.getDisc(newId));
			} else {
				JOptionPane.showMessageDialog(frame, "You must select a disc first.");
			}
		});
		idNumPanel.add(update);
		middle.add(idNumPanel, BorderLayout.PAGE_START);
		
		JButton delete = new JButton("Delete Disc");
		delete.addActionListener(e -> {
			if (idNumField.getText().length() > 0){
				int decision = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to delete this disc?","Confirmation", 
						JOptionPane.YES_NO_OPTION);
				if (decision == JOptionPane.YES_OPTION) {
					lib.getMovie(idNumField.getText()).removeDisc(idNumField.getText());;
					idField.setText("");
					idNumField.setText("");
					info.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(frame, "You must select a disc first.");
			}
		});
		
		panel.add(delete, BorderLayout.PAGE_END);
		panel.add(middle);
		
		return panel;
	}
	
	public void exit() {
		int decision = JOptionPane.showConfirmDialog(
		frame,
		"Do you want to save your changes before exiting?",
		"Exit",
		JOptionPane.YES_NO_CANCEL_OPTION);
		if (decision == JOptionPane.YES_OPTION) {
			ObjectOutputStream out;
			try {
				out = new ObjectOutputStream(new FileOutputStream("lib.ser"));
				out.writeObject(lib);
				out.flush();
				out.close();
				System.exit(0);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(frame, "Unable to write to output file",
						"Error", JOptionPane.WARNING_MESSAGE);
			}
		} else if (decision == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	private class ExitAdapter extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			exit();
		}
	}
	
	/**
	 * Creates the menu for the GUI.
	 * @return menu
	 */
	public JMenu createMenu(){
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		JMenuItem load = new JMenuItem("Load");
		// make a similar save and exit JMenuItem
		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("Exit");
		// Now we work on load
		load.setMnemonic(KeyEvent.VK_L);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		load.addActionListener(e -> {
		ObjectInputStream in;
		try {
			File file = new File("lib.ser");
			if(file.exists()) {
				in = new ObjectInputStream(new FileInputStream("lib.ser"));
				lib = (MovieLibrary)in.readObject();
				in.close();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame,
					"Unable to read from input file \n" +
							e1.getClass().getSimpleName() + ": " + e1.getMessage(),
							"Error",
							JOptionPane.WARNING_MESSAGE);
		}
		});
		menu.add(load);

		save.setMnemonic(KeyEvent.VK_S);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(e -> {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream("lib.ser"));
			out.writeObject(lib);;
			out.flush();
			out.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame, "Unable to write to output file",
					"Error", JOptionPane.WARNING_MESSAGE);
		}
		});
		menu.add(save);
		menu.addSeparator();
		exit.setMnemonic(KeyEvent.VK_X);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exit.addActionListener(e -> exit());
		menu.add(exit);
		return menu;
	}
	
	public static void main(String[] args) {
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            new LibraryGUI();
	        }
	    });
	}
}
