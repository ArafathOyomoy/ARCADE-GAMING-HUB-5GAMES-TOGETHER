package Pacman;

 
import javax.swing.JFrame;

public class pacman extends JFrame{

	public pacman() { 
		this.add(new Body());
	}
	
	public static void pacman_run() {
		pacman p = new pacman();
		
		p.setTitle("Pacman 2D Game");
		p.setSize(376,423);
		p.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		p.setLocationRelativeTo(null);
		p.setVisible(true);
		
	}

}

