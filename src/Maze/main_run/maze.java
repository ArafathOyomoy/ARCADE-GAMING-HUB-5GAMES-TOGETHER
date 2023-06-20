package Maze.main_run;

import javax.swing.JFrame;

public class maze {
    public static void maze_run() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Maze Game");
        
        mypanel panel = new mypanel();
        window.add(panel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.startGameThread(); 
    }
}

