import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;


public class GamePlay extends JComponent {
	private Timer timer;
	private int interval;
	private GameType game;
	private JComponent bar;
	public int height = 25;
	public int width = 26;
	public final Grid grid = new Grid(width, height);
	public int noOpp;
	public Menu menu;
	public JFrame frame;
	//Getter methods
	public Timer getTimer(){
		return timer;
	}
	public int getInterval(){
		return interval;
	}
	public GamePlay(JFrame frame, Menu menu, int difficulty, Mode mode, int noOpp){
		this.frame = frame;
		this.menu = menu;
		this.noOpp = noOpp;
		if(mode == Mode.Snake1){
			game = new SingleSnake1(frame, menu, this);
		} else if (mode == Mode.Snake2){
			game = new SingleSnake2(frame, menu, this);
		} else if (mode == Mode.Snake1WO){
			game = new Snake1WO(frame, menu, this);
		} else if (mode == Mode.Snake2WO){
			game = new Snake2WO(frame, menu, this);
		} else if (mode == Mode.MPSnake2){
			game = new MPSnake2(frame, menu, this);
		}
		game.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setFocusable(true);
		interval = difficulty;
		timer = new Timer(interval, new ActionListener() {
			public void actionPerformed(ActionEvent e) { game.tick(); }});
		timer.start(); 
		game.setLayout(new GridLayout(height, width));
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++){
				game.add(grid.grid[i][j]);
			}
		}
		bar = game.getBar();
		

		add(game, BorderLayout.CENTER);
		add(bar, BorderLayout.PAGE_END);
	}
	public void start(){
		game.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Paint background, border
		game.paint();
	}


	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}
