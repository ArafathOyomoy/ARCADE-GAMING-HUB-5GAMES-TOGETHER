package Maze.main_run;

import javax.swing.JPanel;


import Maze.Entity.Player;
import Maze.tile.Tilemanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;



public class mypanel extends JPanel implements Runnable{
    final int tile_size= 16;
    final int scale=3;
    public final int final_tile_size=tile_size*scale;
    public final int maxScreencol=16;
    public final int maxScreenrow=12;
    final int screenhieght=final_tile_size*maxScreenrow;
    final int screenwidht=final_tile_size*maxScreencol;

    int FPS = 30;

    Tilemanager TileM = new Tilemanager(this);
    Thread gameThread;
    public collisionchecker checker = new collisionchecker(this);
    KeyHandler keyH= new KeyHandler();
    public Player player = new Player(this,keyH);

    
    mypanel(){
        this.setPreferredSize(new Dimension(screenwidht, screenhieght));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void levelchecker(){
        if(player.x+player.solidArea.x>=720&&player.y+player.solidArea.y>=528)
            TileM.loadmap("/Maze/res/map2.txt");
        else if(player.x+player.solidArea.x>=720&&player.y+player.solidArea.y<=48)
            TileM.loadmap("/Maze/res/map3.txt");
        else if(player.x+player.solidArea.x>=528&&player.x+player.solidArea.x<=576&&player.y+player.solidArea.y>=432&&player.y+player.solidArea.y<=480)
            TileM.loadmap("/Maze/res/map4.txt");
    }
   
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawinterval = 1000000000/FPS; 
        double nextDrawTime = System.nanoTime()+drawinterval;
        
        
        while(gameThread != null){
            
            levelchecker();

            update();

            repaint();
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime/=1000000;
                if(remainingTime<0){
                    remainingTime = 0;
                } 
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawinterval;  
            }catch(InterruptedException e){
                e.printStackTrace();
            }    
        }
    }

    

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        TileM.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
    
    void update(){
        player.update();
    }
    
    
}

