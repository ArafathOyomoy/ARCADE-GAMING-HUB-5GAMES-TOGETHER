package BrickBreaker;


import javax.swing.JFrame;

public class main {
	
	public static void BrickB_run () {
		 
       JFrame obj = new JFrame();
       Gameplay gamePlay = new Gameplay();
       obj.setBounds(10,10,700,600);
       obj.setTitle("JJ(Jumping Jonak)");
       obj.setResizable(false);
       obj.setVisible(true);
       obj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
       obj.add(gamePlay);
	
		 
		
		
		
		
	}

}
 
