package Maze.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Maze.main_run.KeyHandler;
import Maze.main_run.mypanel;

public class Player {
    public int x,y;
    public int speed;
    mypanel panel;
    KeyHandler KeyH2;

    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;
    public int spriteCounter = 0;
    public int spritNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn=false;


    public Player(mypanel panel, KeyHandler keyH){
        this.panel=panel;
        this.KeyH2=keyH;
        solidArea = new Rectangle(10,16 ,28,32);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x=8;
        y=8;
        speed=6;
        direction="down";
    }

    
    public void getPlayerImage(){
        try {
            up1=ImageIO.read(getClass().getResourceAsStream("/Maze/res/player up 1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/Maze/res/player up 2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/Maze/res/player down 1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/Maze/res/player down 2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/Maze/res/player left 1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/Maze/res/player left 2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/Maze/res/player right 1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/Maze/res/player right 2.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        if(KeyH2.uppressed||KeyH2.downpressed||KeyH2.leftpressed||KeyH2.rightpressed){
            if(KeyH2.uppressed){
                direction="up";
            }
            else if(KeyH2.downpressed){
                direction="down";
            }
            else if(KeyH2.rightpressed){
                direction="right";
            }
            else if(KeyH2.leftpressed){
                direction="left";
            }

            collisionOn=false;
            panel.checker.checkTile(this);

            if(collisionOn==false){
                switch (direction) {
                    case "up":
                        y-=speed;
                        break;
                    case "down":
                        y+=speed;
                        break;
                    case "left":
                        x-=speed;
                        break;
                    case "right":
                        x+=speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter>5){
                if(spritNum==2)
                    spritNum=1;
                else if(spritNum==1)
                    spritNum=2; 
                spriteCounter=0;
            }
        } 
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spritNum==1)
                    image=up1;   
                if(spritNum==2)
                    image=up2;
                break;
            case "down":
                if(spritNum==1)
                    image=down1;
                if(spritNum==2)
                    image=down2;
                break;
            case "left":
                if(spritNum==1)
                    image=left1;
                if(spritNum==2)
                    image=left2;
                break;
            case "right":
                if(spritNum==1)
                    image=right1;
                if(spritNum==2)
                    image=right2;
                break;
        }
        g2.drawImage(image,x,y,panel.final_tile_size,panel.final_tile_size,null);
    }
}

