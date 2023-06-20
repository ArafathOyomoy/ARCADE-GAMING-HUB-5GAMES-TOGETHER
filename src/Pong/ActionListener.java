package Pong;
import java.awt.event.*;

public class ActionListener extends KeyAdapter
{
	
	Paddle paddle1;
	 Paddle paddle2;
	 ActionListener(Paddle p1,Paddle p2){
		 paddle1=p1;
		 paddle2=p2;
	 }
	 
	
	public void keyPressed(KeyEvent e) {
		paddle1.keyPressed(e);
		paddle2.keyPressed(e);
		
		
	}
	public void keyReleased(KeyEvent e) {
		paddle1.keyReleased(e);
		paddle2.keyReleased(e);
		
		
	}

}
