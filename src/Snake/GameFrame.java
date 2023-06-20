package Snake;

import javax.swing.JFrame;


public class GameFrame extends JFrame
{
	GameFrame()
	{
		
		
		UIPanel1 UI1 = new UIPanel1();
		this.add(UI1);
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);


		GamePanel panel = new GamePanel();
		this.add(panel);
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		
		
		
		
		
	}
	 
	 
	 

}
