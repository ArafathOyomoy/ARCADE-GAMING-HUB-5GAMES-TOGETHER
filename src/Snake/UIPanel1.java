package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;




public class UIPanel1 extends JPanel
{
	static final int SCREEN_WIDTH =700;
	static final int SCREEN_HEIGHT =700;
	
	UIPanel1()
	{
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
    	this.setLayout(null); // Use null layout to set custom component positions
    	placeComponents();
    	
	}

	public void placeComponents()
	 {
	        JLabel titleLabel1 = new JLabel("Welcome to the Snake Game");
	        titleLabel1.setFont(new Font("Arial", Font.BOLD, 40));
	        titleLabel1.setForeground(Color.CYAN);
	        titleLabel1.setBounds(75, 100 - 20, 2000 - 1282, 30); // Set bounds for the label
            add(titleLabel1);
		 
		    
            JLabel titleLabel2 = new JLabel("The Snake Game");
            titleLabel2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
            titleLabel2.setForeground(Color.GREEN);
            titleLabel2.setBounds(225, 200, 200 + 46, 30); // Set bounds for the label
            add(titleLabel2);
            
            JButton button1 = new JButton("New Game");
            button1.setFont(new Font("Arial", Font.BOLD, 25)); // Set the font size to 25
            button1.setBounds(250, 400, 200, 60); // Set bounds for button 1
          
            button1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Disable PanelUI1 and activate Panel
                    JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class,UIPanel1.this);
                    frame.remove(UIPanel1.this); // Remove UIPanel1 from the frame
                   GamePanel panel = new GamePanel();
                    frame.add(panel);
                    frame.pack();
                    frame.setVisible(true);
                    panel.requestFocus(); // Set focus on Panel to receive key events
       		}
            });
            add(button1);
            
            /*
            JButton button2 = new JButton("Main Menu");
            button2.setFont(new Font("Arial", Font.BOLD, 25)); // Set the font size to 25
            button2.setBounds(250, 500, 200, 60); // Set bounds for button 2
            
            
	        button2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                Frame frame = (Frame) SwingUtilities.getWindowAncestor(UIPanel1.this);

	                frame.remove(UIPanel1.this); // Remove PanelUI1 from the frame
	                
	                //Sajeed update the class name of the main interface.
	                // just change the class name form UIPanel1 to the Class name where the main menu interface will be 
	                UIPanel1 panel2 = new UIPanel1();
	                frame.add(panel2);
	                frame.pack();
	                frame.setVisible(true);
	                panel2.requestFocus(); // Set focus on Panel to receive key events

	            }
	        });
            
            

            add(button2);
            
            */
            

            
		 
	 }
      
     public void ToTheGame(ActionEvent e)
            {
                // Disable PanelUI1 and activate Panel
                JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class,UIPanel1.this);
                frame.remove(UIPanel1.this); // Remove PanelUI1 from the frame
                GamePanel panel2 = new GamePanel();
                frame.add(panel2);
                frame.pack();
                frame.setVisible(true);
                panel2.requestFocus(); // Set focus on Panel to receive key events

            }

}
