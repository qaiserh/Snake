import java.awt.Graphics;

import javax.swing.JComponent;


public abstract class GameType extends JComponent{
	public abstract void tick();
	public abstract void start();
	public abstract void paint();
	public abstract JComponent getBar();
	
}
