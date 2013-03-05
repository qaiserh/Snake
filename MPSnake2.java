import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class MPSnake2 extends GameType {
		private JLabel scorelabel1;
		private JLabel scorelabel2;
		private int score1;
		private int score2;
		private Snake snake1;
		private Snake snake2;
		private Fruit fruit;
		private GamePlay game;
		private JFrame frame;
		private Menu menu;
		
		public MPSnake2(JFrame frame, Menu menu, GamePlay game){
			this.frame = frame;
			this.menu = menu;
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			scorelabel1 = new JLabel("Score 1: 0");
			scorelabel2 = new JLabel("Score 2: 0");
			this.game = game;
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_LEFT && snake2.dir != Direction.RIGHT)
						snake2.dir = Direction.LEFT;
					else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake2.dir != Direction.LEFT)
						snake2.dir = Direction.RIGHT;
					else if (e.getKeyCode() == KeyEvent.VK_UP && snake2.dir != Direction.DOWN)
						snake2.dir = Direction.UP;
					else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake2.dir != Direction.UP)
						snake2.dir = Direction.DOWN;
					else if (e.getKeyCode() == KeyEvent.VK_A && snake1.dir != Direction.RIGHT)
						snake1.dir = Direction.LEFT;
					else if (e.getKeyCode() == KeyEvent.VK_D && snake1.dir != Direction.LEFT)
						snake1.dir = Direction.RIGHT;
					else if (e.getKeyCode() == KeyEvent.VK_W && snake1.dir != Direction.DOWN)
						snake1.dir = Direction.UP;
					else if (e.getKeyCode() == KeyEvent.VK_S && snake1.dir != Direction.UP)
						snake1.dir = Direction.DOWN;
				}
			});
			
		}

		public void tick(){
			if(!snake1.move()){
				game.getTimer().stop();
				if (score1 > score2){
					JOptionPane.showMessageDialog(Game.frame, "Game Over!\nPlayer 1 wins!");
				} else if (score1 < score2){
					JOptionPane.showMessageDialog(Game.frame, "Game Over!\nPlayer 2 wins!");
				} else if (score2 == score1){
					JOptionPane.showMessageDialog(Game.frame, "Game Over!\nNobody wins!");
				}
				frame.remove(game);
				frame.add(menu);
				frame.pack();
				return;
			} else {
				if(snake1.eats(fruit)) {
					snake1.lengthen();
					fruit = new Fruit(game.grid.getFree());
					score1 = score1 + 10;
					scorelabel1.setText("Score 1: " + score1);
				}
			}
			if(!snake2.move()){
				game.getTimer().stop();
				if (score1 > score2){
					JOptionPane.showMessageDialog(Game.frame, "Game Over!\nPlayer 1 wins!");
				} else if (score1 < score2){
					JOptionPane.showMessageDialog(Game.frame, "Game Over!\nPlayer 2 wins!");
				} else if (score1 == score2){
					JOptionPane.showMessageDialog(Game.frame, "Game Over!\nNobody wins!");
				}
				frame.remove(game);
				frame.add(menu);
				frame.pack();
			} else {
				if(snake2.eats(fruit)) {
					snake2.lengthen();
					fruit = new Fruit(game.grid.getFree());
					score2 = score2 + 10;
					scorelabel2.setText("Score 2: " + score2);
				}
			}
			game.repaint();

		}

		public void start() {
			fruit = new Fruit(game.grid.getFree());
			snake1 = new Snake2(game.grid, game.grid.grid[game.width/2][game.height/3]);
			snake2 = new Snake2(game.grid, game.grid.grid[game.width/2][2 * game.height/3]);
			requestFocusInWindow();
		}

		public void paint() {
			snake1.draw();
			snake2.draw();
			fruit.draw();
		}



		public JComponent getBar() {
			JPanel scoreboard = new JPanel();
			scoreboard.setLayout(new GridLayout(1, 2));
			scoreboard.add(scorelabel1);
			scoreboard.add(scorelabel2);
			return scoreboard;
		}


	

}
