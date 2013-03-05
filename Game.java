import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game implements Runnable {
	static final JFrame frame = new JFrame("Snake");
   public void run() {
      // Top-level frame
      frame.setLocation(300, 100);

      // Main Menu
      	final Menu menu = new Menu(frame);
      	frame.add(menu, BorderLayout.CENTER);
     // final GamePlay field = new GamePlay(100, Mode.Snake2, 3);
      //frame.add(field, BorderLayout.CENTER);


      // Put the frame on the screen
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      // Start the game running
      //field.start();
      }

   /*
    * Get the game started!
    */
   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Game());
   }

}
