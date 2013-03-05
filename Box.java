import javax.swing.JComponent;


public class Box extends JComponent {
	private int i;
	private int j;
	public boolean occupied;
	
	public Box(int i, int j){
		this.i = i;
		this.j = j;
	}
	public int geti(){
		return i;
	}
	public int getj(){
		return j;
	}
	
}
