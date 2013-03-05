import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Snake2WO extends GameType {
	private JLabel scorelabel;
	private int score;
	private Snake snake;
	private Snake[] opp;
	private Fruit fruit;
	private GamePlay game;
	private JFrame frame;
	private Menu menu;
	
	public Snake2WO(JFrame frame, Menu menu, GamePlay game){
		this.frame = frame;
		this.menu = menu;
		opp = new Snake[3];
		scorelabel = new JLabel("Score: 0");
		this.game = game;
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.dir != Direction.RIGHT)
					snake.dir = Direction.LEFT;
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.dir != Direction.LEFT)
					snake.dir = Direction.RIGHT;
				else if (e.getKeyCode() == KeyEvent.VK_UP && snake.dir != Direction.DOWN)
					snake.dir = Direction.UP;
				else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.dir != Direction.UP)
					snake.dir = Direction.DOWN;
			}
		});
	}
	public void tick() {
		Random r = new Random();
		for (int i = 0; i < game.noOpp; i++){
			int d = r.nextInt(4);
			if(d == 0){
				opp[i].dir = Direction.UP;
			} else if(d == 1){
				opp[i].dir = Direction.RIGHT;
			} else if(d == 2){
				opp[i].dir = Direction.DOWN;
			} else if(d == 3){
				opp[i].dir = Direction.LEFT;
			}
			opp[i].move();
		}
		if(!snake.move()){
			game.getTimer().stop();
			new DialogBox(score, Mode.Snake2WO, frame, menu, game);
		} else {
			if(snake.eats(fruit)) {
				snake.lengthen();
				fruit = new Fruit(game.grid.getFree());
				score = score + 10;
				scorelabel.setText("Score: " + score);
			}
			for(int i = 0; i < game.noOpp;i++){
				if(opp[i].eats(fruit)){
					opp[i].lengthen();
					fruit = new Fruit(game.grid.getFree());
				}
			}
		}
		game.repaint();

	}

	@Override
	public void start() {
		fruit = new Fruit(game.grid.getFree());
		snake = new Snake2(game.grid, game.grid.grid[game.width/2][game.height/2]);
		for (int i = 0; i < game.noOpp; i++){
			opp[i] = new Snake2(game.grid, game.grid.grid[4][i * 3]);
		}
		requestFocusInWindow();	
	}

	@Override
	public void paint() {
		snake.draw();
		fruit.draw();
		for(int i = 0; i < game.noOpp; i++){
			opp[i].draw();
		}
	}

	@Override
	public JComponent getBar() {
		return scorelabel;
	}

}
