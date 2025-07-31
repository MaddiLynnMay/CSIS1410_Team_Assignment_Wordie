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

		topicBox();

		controlPanel();

		gameBox();

	}

	/**
	 * This is for the center part or "main" part of the game. Players field.
	 */
	private void gameBox() {
		JPanel playingField = new JPanel();
		playingField.setBackground(Color.DARK_GRAY);
		contentPane.add(playingField, BorderLayout.CENTER);

		// Set layout to 6 rows x 5 columns
		playingField.setLayout(new GridLayout(6, 5, 5, 5));

		/**
		 * Row A
		 */
		JLabel A1 = new JLabel("", JLabel.CENTER);
		A1.setOpaque(true);
		A1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(A1);
		JLabel A2 = new JLabel("", JLabel.CENTER);
		A2.setOpaque(true);
		A2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(A2);
		JLabel A3 = new JLabel("", JLabel.CENTER);
		A3.setOpaque(true);
		A3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(A3);
		JLabel A4 = new JLabel("", JLabel.CENTER);
		A4.setOpaque(true);
		A4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(A4);
		JLabel A5 = new JLabel("", JLabel.CENTER);
		A5.setOpaque(true);
		A5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(A5);

		/**
		 * Row B
		 */
		JLabel B1 = new JLabel("", JLabel.CENTER);
		B1.setOpaque(true);
		B1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(B1);
		JLabel B2 = new JLabel("", JLabel.CENTER);
		B2.setOpaque(true);
		B2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(B2);
		JLabel B3 = new JLabel("", JLabel.CENTER);
		B3.setOpaque(true);
		B3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(B3);
		JLabel B4 = new JLabel("", JLabel.CENTER);
		B4.setOpaque(true);
		B4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(B4);
		JLabel B5 = new JLabel("", JLabel.CENTER);
		B5.setOpaque(true);
		B5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(B5);

		/**
		 * Row C
		 */
		JLabel C1 = new JLabel("", JLabel.CENTER);
		C1.setOpaque(true);
		C1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(C1);
		JLabel C2 = new JLabel("", JLabel.CENTER);
		C2.setOpaque(true);
		C2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(C2);
		JLabel C3 = new JLabel("", JLabel.CENTER);
		C3.setOpaque(true);
		C3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(C3);
		JLabel C4 = new JLabel("", JLabel.CENTER);
		C4.setOpaque(true);
		C4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(C4);
		JLabel C5 = new JLabel("", JLabel.CENTER);
		C5.setOpaque(true);
		C5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(C5);

		/**
		 * Row D
		 */
		JLabel D1 = new JLabel("", JLabel.CENTER);
		D1.setOpaque(true);
		D1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(D1);
		JLabel D2 = new JLabel("", JLabel.CENTER);
		D2.setOpaque(true);
		D2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(D2);
		JLabel D3 = new JLabel("", JLabel.CENTER);
		D3.setOpaque(true);
		D3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(D3);
		JLabel D4 = new JLabel("", JLabel.CENTER);
		D4.setOpaque(true);
		D4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(D4);
		JLabel D5 = new JLabel("", JLabel.CENTER);
		D5.setOpaque(true);
		D5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(D5);

		/**
		 * Row E
		 */
		JLabel E1 = new JLabel("", JLabel.CENTER);
		E1.setOpaque(true);
		E1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(E1);
		JLabel E2 = new JLabel("", JLabel.CENTER);
		E2.setOpaque(true);
		E2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(E2);
		JLabel E3 = new JLabel("", JLabel.CENTER);
		E3.setOpaque(true);
		E3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(E3);
		JLabel E4 = new JLabel("", JLabel.CENTER);
		E4.setOpaque(true);
		E4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(E4);
		JLabel E5 = new JLabel("", JLabel.CENTER);
		E5.setOpaque(true);
		E5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playingField.add(E5);

		JLabel[] rowA = { A1, A2, A3, A4, A5 };
		JLabel[] rowB = { B1, B2, B3, B4, B5 };
		JLabel[] rowC = { C1, C2, C3, C4, C5 };
		JLabel[] rowD = { D1, D2, D3, D4, D5 };
		JLabel[] rowE = { E1, E2, E3, E4, E5 };

		rowArray = new JLabel[][] { rowA, rowB, rowC, rowD, rowE };

		/**
		 * Empty box section 1 to keep input box centered
		 */
		JLabel emptyBox1 = new JLabel();
		emptyBox1.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(emptyBox1);
		JLabel emptyBox2 = new JLabel();
		emptyBox2.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(emptyBox2);

		/**
		 * Input your words here
		 */
		txtEnterWordHere = new JTextField();
		txtEnterWordHere.setText("Enter word here");
		txtEnterWordHere.setToolTipText("Enter a word to see if you got the guess correct or partially correct");
		txtEnterWordHere.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtEnterWordHere.setBackground(new Color(240, 240, 240));
		txtEnterWordHere.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		txtEnterWordHere.setForeground(Color.DARK_GRAY);
		txtEnterWordHere.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(txtEnterWordHere);
		txtEnterWordHere.setColumns(10);

		// This makes it so only letters and backspace can be added to the text field.
		// This also makes it so all the letters get capitalized so it looks nicer and
		// is more uniform.
		txtEnterWordHere.addKeyListener(new java.awt.event.KeyAdapter() {
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

		// When the user clicks in the text field, the text disappears so the user can
		// start typing their word. The text reappears when the user is no longer in the
		// text field.

		txtEnterWordHere.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				txtEnterWordHere.setText("");

			}

			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtEnterWordHere.getText().isEmpty()) {
					txtEnterWordHere.setText("Enter word here");
				}
			}
		});

		/**
		 * Empty box section 2 to keep input box centered
		 */
		JLabel emptyBox3 = new JLabel();
		emptyBox3.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(emptyBox3);
		JLabel emptyBox4 = new JLabel();
		emptyBox4.setHorizontalAlignment(SwingConstants.CENTER);
		playingField.add(emptyBox4);

	}

	/**
	 * Section for the reset and submit button.
	 */
	private void controlPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.DARK_GRAY);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		{
			// This is the submit button.
			JButton confirm = new JButton("Submit");
			confirm.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					String userGuessedWord = txtEnterWordHere.getText();
					Topics selectedTopic = (Topics) topicSelector.getSelectedItem();
					// if the user hasn't chosen a topic yet, it will prompt the user to select one.

					if (selectedTopic == null) {
						JOptionPane.showMessageDialog(null, "Please choose a topic before starting the game.");
						return;
					}

					String actualWord = setUp.getSelectedWord();
					// each time the user adds a word, the guess count goes up one.
					// But if the user doesn't add a five letter word, either too many or too few
					// letters, the guess count won't go up and instead the user will be prompted to
					// add a five letter word.
					if (wordieCheck.letterCountCheck(userGuessedWord)) {
						wordieCheck.compareWords(userGuessedWord, actualWord, rowArray[guessCount], guessCount);
						guessCount++;
					} else {
						JOptionPane.showMessageDialog(WordieApp.this, "Please enter a 5-letter word.");
					}
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
					// resets the guess count so the user can replay
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
					topicSelector.setEnabled(true);

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
	 * Topic box
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
			topicSelector.addItem(null);
			for (Topics t : Topics.values()) {
				topicSelector.addItem(t);
			}

			// chooses the word from the topic. Once topic is selected, user can't change it
			topicSelector.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Topics selected = (Topics) topicSelector.getSelectedItem();
					if (selected != null) {
						try {
							setUp.loadTopicWords(selected);
							topicSelector.setEnabled(false);
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
