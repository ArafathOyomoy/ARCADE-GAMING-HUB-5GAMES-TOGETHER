package Maze.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Maze.main_run.mypanel;

public class Tilemanager {
    mypanel panel;
    public Tile[] tile;
    public int mapTileNum[][];

    public Tilemanager(mypanel panel){
        this.panel=panel;

        tile = new Tile[10];
        mapTileNum = new int[panel.maxScreencol+1][panel.maxScreenrow+1];

        getTileImage();
        loadmap("/Maze/res/map1.txt");
    }

    public void getTileImage(){
        try {
            tile[0]=new Tile();
            tile[0].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/ground.png"));
            tile[1]=new Tile();
            tile[1].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/wall.png"));
            tile[1].collision=true;
            tile[2]=new Tile();
            tile[2].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/heart tile.png"));
            tile[3]=new Tile();
            tile[3].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/G.png"));
            tile[3].collision=true;
            tile[4]=new Tile();
            tile[4].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/A.png"));
            tile[4].collision=true;
            tile[5]=new Tile();
            tile[5].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/M.png"));
            tile[5].collision=true;
            tile[6]=new Tile();
            tile[6].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/E.png"));
            tile[6].collision=true;
            tile[7]=new Tile();
            tile[7].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/O.png"));
            tile[7].collision=true;
            tile[8]=new Tile();
            tile[8].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/V.png"));
            tile[8].collision=true;
            tile[9]=new Tile();
            tile[9].image=ImageIO.read(getClass().getResourceAsStream("/Maze/res/R.png"));
            tile[9].collision=true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadmap(String map){
        try {
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;
            
            while(col<panel.maxScreencol&&row<panel.maxScreenrow){
                String line = br.readLine();
                while(col<panel.maxScreencol){
                    String nums[] = line.split(" ");
                    int num = Integer.parseInt(nums[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col==panel.maxScreencol){
                    col=0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            
        }
    }

    public void draw(Graphics2D g2){
        int col=0;
        int row=0;
        int x=0;
        int y=0;
        while(col<panel.maxScreencol&&row<panel.maxScreenrow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, panel.final_tile_size, panel.final_tile_size, null);
            
            col++;
            x+=panel.final_tile_size;
            if(col==panel.maxScreencol){
                col=0;
                x=0;
                row++;
                y+=panel.final_tile_size;
            }    
        }
        

    }
}

