import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;


public class Fruit {
	private Box position;
	public Fruit(LinkedList<Box> free){
		Random r = new Random();
		int p = r.nextInt(free.size());
		this.position = free.get(p);
	}
	public Box getPosition(){
		return position;
	}
	public void draw() {
		Graphics g = position.getGraphics();
		int width = position.getWidth();
		int height = position.getHeight();
		g.fillOval(0, 0, width, height);
		
	}
}
