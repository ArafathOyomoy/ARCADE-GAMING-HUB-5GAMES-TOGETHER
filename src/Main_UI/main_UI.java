package Main_UI; 

import java.awt.Color;  
import java.awt.Dimension;   
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import Maze.main_run.maze;
import Pacman.pacman;
import Pong.gameMain;
import Snake.driverClass;

 
public class main_UI{
    
    public static void main(String[] args) {
        JFrame main_Frame = new JFrame("Arcade");
        main_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_Frame.setLayout(new GridLayout(5,1));
        main_Frame.setVisible(true);
        main_Frame.setResizable(false);

        JButton Maze = new JButton("Maze");
        JButton Pacman = new JButton("Pacman");
        JButton BrickB = new JButton("Brick Breaker");
        JButton Pong = new JButton("Pong");
        JButton Snake = new JButton("Snake");

        Maze.setFocusable(false);
        Pacman.setFocusable(false);
        BrickB.setFocusable(false);
        Pong.setFocusable(false);
        Snake.setFocusable(false);

        Maze.setPreferredSize(new Dimension(400,100));
        Pacman.setPreferredSize(new Dimension(400,100));
        BrickB.setPreferredSize(new Dimension(400,100));
        Pong.setPreferredSize(new Dimension(400,100));
        Snake.setPreferredSize(new Dimension(400,100));

        Maze.setBackground(Color.DARK_GRAY);
        Pacman.setBackground(Color.DARK_GRAY);
        BrickB.setBackground(Color.DARK_GRAY);
        Pong.setBackground(Color.DARK_GRAY);
        Snake.setBackground(Color.DARK_GRAY);

        Maze.setForeground(Color.RED);
        Pacman.setForeground(Color.YELLOW);
        BrickB.setForeground(Color.ORANGE);
        Pong.setForeground(Color.PINK);
        Snake.setForeground(Color.GREEN);

        Maze.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        Pacman.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        BrickB.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        Pong.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        Snake.setFont(new Font("Comic Sans", Font.PLAIN, 30));

        Maze.addActionListener(e -> maze.maze_run());
        Pacman.addActionListener(e -> pacman.pacman_run());
        BrickB.addActionListener(e -> BrickBreaker.main.BrickB_run());
        Pong.addActionListener(e -> gameMain.pong_run());
        Snake.addActionListener(e -> driverClass.snake_run(args));

        main_Frame.add(Maze);
        main_Frame.add(Pacman);
        main_Frame.add(BrickB);
        main_Frame.add(Pong);
        main_Frame.add(Snake);
        main_Frame.pack();
    }

}
