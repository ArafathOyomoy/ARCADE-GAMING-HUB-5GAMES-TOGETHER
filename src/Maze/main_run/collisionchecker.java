package Maze.main_run;

import Maze.Entity.Player;

public class collisionchecker {
    mypanel panel;

    public collisionchecker(mypanel panel){
        this.panel=panel;
    } 

    public void checkTile(Player player){
        int playerleftx=player.x+player.solidArea.x;
        int playerrightx=player.x+player.solidArea.x+player.solidArea.width;
        int playertopy=player.y+player.solidArea.y;
        int playerbottomy=player.y+player.solidArea.y+player.solidArea.height;

        int playerleftcol=playerleftx/panel.final_tile_size;
        int playerrightcol=playerrightx/panel.final_tile_size;
        int playertoprow=playertopy/panel.final_tile_size;
        int playerbottomrow=playerbottomy/panel.final_tile_size;

        int tilenum1,tilenum2;
        
        switch(player.direction){
            case "up":
                playertoprow=(playertopy-player.speed)/panel.final_tile_size;
                tilenum1=panel.TileM.mapTileNum[playerleftcol][playertoprow];
                tilenum2=panel.TileM.mapTileNum[playerrightcol][playertoprow];
                if(panel.TileM.tile[tilenum1].collision||panel.TileM.tile[tilenum2].collision){
                    player.collisionOn=true;
                }
                break;
            case "down":
                playerbottomrow=(playerbottomy+player.speed)/panel.final_tile_size;
                tilenum1=panel.TileM.mapTileNum[playerleftcol][playerbottomrow];
                tilenum2=panel.TileM.mapTileNum[playerrightcol][playerbottomrow];
                if(panel.TileM.tile[tilenum1].collision||panel.TileM.tile[tilenum2].collision){
                    player.collisionOn=true;
                }
                break;
            case "left":
                playerleftcol=(playerleftx-player.speed)/panel.final_tile_size;
                tilenum1=panel.TileM.mapTileNum[playerleftcol][playertoprow];
                tilenum2=panel.TileM.mapTileNum[playerleftcol][playerbottomrow];
                if(panel.TileM.tile[tilenum1].collision||panel.TileM.tile[tilenum2].collision){
                    player.collisionOn=true;
                }
                break;
            case "right":
                playerrightcol=(playerrightx-player.speed)/panel.final_tile_size;
                tilenum1=panel.TileM.mapTileNum[playerrightcol][playertoprow];
                tilenum2=panel.TileM.mapTileNum[playerrightcol][playerbottomrow];
                if(panel.TileM.tile[tilenum1].collision||panel.TileM.tile[tilenum2].collision){
                    player.collisionOn=true;
                }
                break;
        }
    }

}

