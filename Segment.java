import java.awt.Color;
import java.awt.Graphics;



public class Segment {
	public Box position;
	public Segment next;
	public Segment(Box position, Segment next){
		this.position = position;
		this.next = next;
	}
	public void draw() {
		Graphics g = position.getGraphics();
		//head is red
		if (this.next == null) g.setColor(Color.red);
		//tail is black
		else g.setColor(Color.black);
		int w = position.getWidth();
		int h = position.getHeight();
		g.fillRect(0, 0, w, h);
	}
	
}
