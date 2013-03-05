import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class SingleSnake2 extends GameType{
	private JLabel scorelabel;
	private int score;
	private Snake snake;
	private Fruit fruit;
	private GamePlay game;
	private JFrame frame;
	private Menu menu;
	
	public SingleSnake2(JFrame frame, Menu menu, GamePlay game){
		this.frame = frame;
		this.menu = menu;
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

	public void tick(){
		if(!snake.move()){
			game.getTimer().stop();
			new DialogBox(score, Mode.Snake2, frame, menu, game);
		} else {
			if(snake.eats(fruit)) {
				snake.lengthen();
				fruit = new Fruit(game.grid.getFree());
				score = score + 10;
				scorelabel.setText("Score: " + score);
			}
		}
		game.repaint();

	}

	public void start() {
		fruit = new Fruit(game.grid.getFree());
		snake = new Snake2(game.grid, game.grid.grid[game.width/2][game.height/2]);
		requestFocusInWindow();
	}

	public void paint() {
		snake.draw();
		fruit.draw();
	}

	public JComponent getBar() {
		return scorelabel;
	}




}
