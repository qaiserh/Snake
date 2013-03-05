import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;




@SuppressWarnings("serial")
public class HighScores extends JComponent{

	private JButton back;
	private JFrame frame;
	private Menu menu;
	private JPanel board;
	
	public HighScores(JFrame frame, Menu menu){
		this.frame = frame;
		this.menu = menu;
		board = new JPanel();
		board.setLayout(new GridLayout(11, 5));
		board.add(new JPanel());
		board.add(new JLabel("Name"));
		board.add(new JLabel("Score"));
		board.add(new JLabel("Mode"));
		board.add(new JLabel("Difficulty"));
		for(int i = 0; i < 10; i++){
			board.add(new JLabel(Integer.toString(i + 1)));
			board.add(new JLabel(menu.HSNames[i]));
			board.add(new JLabel(Integer.toString(menu.HScores[i])));
			board.add(new JLabel(menu.HSModes[i]));
			board.add(new JLabel(menu.difs[i]));
		}
		//back button
		back = new JButton("Back");
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				back();
			}
		});
		setLayout(new BorderLayout());
		add(back, BorderLayout.PAGE_END);
		add(board, BorderLayout.CENTER);
	}
	
	public void back(){
		frame.remove(this);
		frame.add(menu);
		frame.pack();		
	}

}
