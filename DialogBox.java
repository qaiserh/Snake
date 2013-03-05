import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class DialogBox {
	private Mode mode;
	private JFrame frame;
	private boolean highscore;
	private String scoremode;
	private int index;
	public DialogBox(int score, Mode mode, JFrame frame, Menu menu, GamePlay g){
		this.mode = mode;
		this.frame = frame;

		if(mode == Mode.Snake1) scoremode = "Snake 1";
		if(mode == Mode.Snake2) scoremode = "Snake 2";
		if(mode == Mode.Snake1WO) scoremode = "Snake 1 With Opp";
		if(mode == Mode.Snake2WO) scoremode = "Snake 2 With Opp";
		int i = 0;
		while(i < 10 && !highscore){
			if(menu.HScores[i] < score){
				highscore = true;
				index = i;
			}
			i++;
		}
		int d = menu.difficulty;
		if (highscore) {
			String name = JOptionPane.showInputDialog("Game Over!\nYour score: " + score + "\nNew High Score!\nEnter Name:");
			menu.HScores[index] = score;
			menu.HSNames[index] = name;
			menu.HSModes[index] = scoremode;
			if (d == 150) menu.difs[index] = "Easy";
			if (d == 100) menu.difs[index] = "Medium";
			if (d == 50) menu.difs[index] = "Hard";
			
		} else {
			JOptionPane.showMessageDialog(frame, "Game Over!\nYour score: " + score);
		}
		frame.remove(g);
		frame.add(menu);
		frame.pack();
	}
}
