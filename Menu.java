import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class Menu extends JComponent implements Runnable {
	private Mode gmode;
	private JFrame frame;
	public int difficulty;
	public String[] HSNames;
	public int[] HScores;
	public String[] HSModes;
	public String[] difs;

	public Menu(JFrame frame){
		this.frame = frame; //So that the same frame is used when the game starts.
		setLayout(new BorderLayout());
		//Creates High Score Table
		HSNames = new String[10];
		HScores = new int[10];
		HSModes = new String[10];
		difs = new String[10];
		for(int i = 0; i < 10; i++){
			HSNames[i] = "Hamza";
			HScores[i] = 0;
			HSModes[i] = "Snake 1";
			difs[i] = "Difficulty";
		}
		//Game title
		JLabel title = new JLabel("Snake!!", SwingConstants.CENTER);
		Font f = new Font(null, Font.PLAIN, 36); //Bigger font
		title.setFont(f);
		title.setBorder(BorderFactory.createLineBorder(Color.black));

		//Game instructions
		String inst = "It's the classic game Snake! \n" +
				"Use the arrow keys to move the snake around the field.\n" +
				"Make it eat the fruit and it will grow longer.\n" +
				"If it eats its own tail, game over!\n \n" +
				"There are multiple modes:\n" +
				"Snake1: Traditional snake. You cannot hit the walls.\n" +
				"Snake2: The snake wraps around when it reaches a wall.\n" +
				"Snake1 With Opponents: Compete with 3 other, rather stupid\n" +
				"snakes on the board.\n" +
				"Snake2 With Opponents: I think you get the idea\n" +
				"Two-player Snake: Player 1 uses w,a,s,d, player two the arrow keys.\n" +
				"Compete to get the fruit!";
		JTextPane instructions = new JTextPane();
		instructions.setText(inst);
		instructions.setEditable(false);

		//Make the buttons for the different modes;
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(7,1));
		buttons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.white);
		buttons.setFocusable(true);
		JButton Snake1 = makeButton("Snake 1", Mode.Snake1);
		JButton Snake2 = makeButton("Snake 2", Mode.Snake2);
		JButton Snake1WO = makeButton("Snake 1 With Opponents", Mode.Snake1WO);
		JButton Snake2WO = makeButton("Snake 2 With Opponents", Mode.Snake2WO);
		JButton SnakeTwo = makeButton("Two-player Snake", Mode.MPSnake2);
		JButton Highscore = makeButton("High Scores", Mode.Highscores);
		buttons.add(Snake1);
		buttons.add(Snake2);
		buttons.add(Snake1WO);
		buttons.add(Snake2WO);
		buttons.add(SnakeTwo);
		buttons.add(Highscore);

		//Difficulty setting
		difficulty = 150;
		ButtonGroup d = new ButtonGroup();
		JRadioButton easy = makeRadioButton("Easy", d, 150);
		JRadioButton med = makeRadioButton("Medium", d, 100);
		JRadioButton hard = makeRadioButton("Hard", d, 50);
		easy.doClick();
		JPanel diff = new JPanel();
		diff.setLayout(new GridLayout(1,3));
		diff.add(easy);
		diff.add(med);
		diff.add(hard);
		buttons.add(diff);

		//Lay everything out.
		add(title, BorderLayout.PAGE_START);
		add(instructions, BorderLayout.CENTER);
		add(buttons, BorderLayout.PAGE_END);
	}
	//Method that makes buttons.
	public JButton makeButton(String s, final Mode mode) {
		JButton b = new JButton(s);
		b.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				gmode = mode;
				run();
			}
		});
		return b;
	}
	//Method that makes JRadioButtons
	public JRadioButton makeRadioButton(String s, ButtonGroup group, final int diff) {
		JRadioButton b = new JRadioButton(s);
		b.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				difficulty = diff;
			}
		});
		group.add(b);
		return b;
	}

	@Override
	public void run() {
		if (gmode == Mode.Highscores){
			frame.remove(this);
			frame.add(new HighScores(frame, this));
			frame.pack();
		} else {
			frame.remove(this);
			GamePlay g = new GamePlay(frame, this, difficulty, gmode, 3);
			frame.add(g, BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
			g.start();
		}
	}
}
