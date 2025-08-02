/**
 * Team Project for CSIS 1410
 * @author Jon Cardenas, Elisabeth Gondolo and Madelyn May
 * This is a project to display the game "wordie"
 * How to play: Guess the word from the topic. If you get part of it right, it will
 * uncover some boxes to give you a hint to get closer. You have 5 attempts to guess the word correctly.
 */
package wordie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class WordieApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEnterWordHere;
	private WordieCheck wordieCheck = new WordieCheck();
	private SetUp setUp = new SetUp();
	private int guessCount = 0;
	private JLabel[][] rowArray;
	private JComboBox<Topics> topicSelector;
	private JButton confirm;
	private JLabel chosenTopic;

	/**
	 * Launch the application and have the frame centered.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordieApp frame = new WordieApp();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					ImageIcon wordieLogo = new ImageIcon("Resources/w.png");
					frame.setIconImage(wordieLogo.getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public WordieApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Wordie - Guess the Word!");

		topicBox(); // contains topic drop-down, and displays chosen topic

		controlPanel(); // contains the submit and reset buttons

		gameBox(); // contains array for columns/rows, user text field, KeyListner and
					// ActionListener

	}

	/**
	 * This is for the center part or "main" part of the game. Players field. This
	 * has 5 rows and 5 columns for letters and a user field.
	 */
	private void gameBox() {
		JPanel playingField = new JPanel();
		playingField.setBackground(Color.DARK_GRAY);
		contentPane.add(playingField, BorderLayout.CENTER);

		// Set layout to 6 rows x 5 columns
		playingField.setLayout(new GridLayout(6, 5, 5, 5));

		rowArray = new JLabel[5][5];

		// Loop to create 5 rows with 5 columns each. This is where each letter will go.
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				JLabel label = new JLabel("", JLabel.CENTER);
				label.setOpaque(true);
				label.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				playingField.add(label);
				rowArray[row][col] = label;
			}
		}

		// Empty box section 1 to keep input box centered

		JLabel emptyBox1 = new JLabel();
		emptyBox1.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(emptyBox1);
		JLabel emptyBox2 = new JLabel();
		emptyBox2.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(emptyBox2);

		// Text field for the user.

		txtEnterWordHere = new JTextField();
		txtEnterWordHere.setText("Enter word here");
		txtEnterWordHere.setToolTipText("Enter a word to see if you got the guess correct or partially correct");
		txtEnterWordHere.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtEnterWordHere.setBackground(new Color(240, 240, 240));
		txtEnterWordHere.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		txtEnterWordHere.setForeground(Color.DARK_GRAY);
		txtEnterWordHere.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(txtEnterWordHere);

		// When user types, it removes the"Enter word here"

		txtEnterWordHere.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if (txtEnterWordHere.getText().equals("Enter word here")) {
					txtEnterWordHere.setText("");
				}
			}

			// Only capital letters and backspace can be added to the text field

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				} else if (c != '\b') {
					e.consume();
				}
			}
		});

		// Pressing Enter on the keyboard acts like clicking Submit.

		txtEnterWordHere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirm.doClick();
			}
		});

		// Empty box section 2 to keep input box centered

		JLabel emptyBox3 = new JLabel();
		emptyBox3.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(emptyBox3);
		JLabel emptyBox4 = new JLabel();
		emptyBox4.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(emptyBox4);
	}

	/**
	 * Section for the reset and submit buttons.
	 */
	private void controlPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.DARK_GRAY);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		{
			// This is the submit button.
			confirm = new JButton("Submit");
			confirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					String userGuessedWord = txtEnterWordHere.getText();
					Topics selectedTopic = (Topics) topicSelector.getSelectedItem();

					// if the user hasn't chosen a topic yet, it will prompt the user to select one.

					if (selectedTopic == null) {
						JOptionPane.showMessageDialog(null, "Please choose a topic before starting the game.");
						return;
					}

					String actualWord = setUp.getSelectedWord();
					// Checks the word entered is a five-letter word.
					// Guess count goes up one if valid five-letter word.

					if (wordieCheck.letterCountCheck(userGuessedWord)) {
						wordieCheck.compareWords(userGuessedWord, actualWord, rowArray[guessCount], guessCount);
						guessCount++;
					} else {
						JOptionPane.showMessageDialog(WordieApp.this, "Please enter a 5-letter word.");
					}
					txtEnterWordHere.setText("Enter word here");
					txtEnterWordHere.requestFocusInWindow();
				}
			});

			confirm.setBackground(new Color(0, 123, 255));
			confirm.setForeground(Color.WHITE);
			confirm.setOpaque(true);
			confirm.setBorderPainted(false);
			buttonPanel.add(confirm);

		}
		{
			// This is the reset button.
			JButton restart = new JButton("Reset");
			restart.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// resets the guess count
					guessCount = 0;
					// resets the letters and background
					for (JLabel[] row : rowArray) {
						for (JLabel cell : row) {
							cell.setText("");
							cell.setBackground(new JLabel().getBackground());
						}
					}

					// resets the user text field and drop-down
					txtEnterWordHere.setText("Enter word here");
					topicSelector.setSelectedItem(null);
					topicSelector.setVisible(true);
					chosenTopic.setVisible(false);
				}

			});
			restart.setBackground(Color.GRAY);
			restart.setForeground(Color.WHITE);
			restart.setOpaque(true);
			restart.setBorderPainted(false);
			buttonPanel.add(restart);

		}
	}

	/**
	 * This is for the Topic box at the top of the game. It has a JComboBox to
	 * display the drop-down options. When the user chooses the topic, the JCombobox
	 * goes invisible and instead displays the chosen topic.
	 * 
	 */
	private void topicBox() {
		JPanel topics = new JPanel();
		topics.setBackground(Color.BLACK);
		contentPane.add(topics, BorderLayout.NORTH);

		JLabel findingTopic = new JLabel("Your topic: ");
		findingTopic.setForeground(Color.WHITE);
		findingTopic.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		topics.add(findingTopic);
		{
			// this is the drop-down option for the topic
			topicSelector = new JComboBox<>();

			chosenTopic = new JLabel("");
			chosenTopic.setForeground(Color.WHITE);
			chosenTopic.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
			chosenTopic.setVisible(false);
			topics.add(chosenTopic);

			topicSelector.addItem(null);
			for (Topics t : Topics.values()) {
				topicSelector.addItem(t);
			}

			// This is a setRenderer to change the placeholder in the drop-down to "Choose your topic"
			topicSelector.setRenderer(new javax.swing.ListCellRenderer<Topics>() {

				@Override
				public Component getListCellRendererComponent(JList<? extends Topics> list, Topics value, int index,
						boolean isSelected, boolean cellHasFocus) {

					JLabel label;

					if (value == null) {
						label = new JLabel("Choose Your Topic");
					} else {
						label = new JLabel(value.name());
					}

					if (isSelected) {
						label.setBackground(Color.LIGHT_GRAY);
						label.setOpaque(true);
					} else {
						label.setBackground(Color.WHITE);
						label.setOpaque(true);
					}

					return label;
				}
			});

			// When topic is chosen by the user, the chosen topic is displayed

			topicSelector.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Topics selected = (Topics) topicSelector.getSelectedItem();
					if (selected != null) {
						try {
							setUp.loadTopicWords(selected);
							topicSelector.setVisible(false);
							chosenTopic.setText(selected.name());
							chosenTopic.setVisible(true);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			});

			topics.add(topicSelector);
		}

	}
}
