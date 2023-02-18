package org.game.main;

import org.game.object.OBJ_Chest;
import org.game.object.OBJ_Door;
import org.game.object.OBJ_Key;
import org.game.object.OBJ_Wings;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

   public void setObject() {

        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 24 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 25 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 26 * gp.tileSize;
        gp.obj[3].worldY = 8 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 27 * gp.tileSize;
        gp.obj[4].worldY = 7 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 28 * gp.tileSize;
        gp.obj[5].worldY = 7 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 29 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;

        gp.obj[7] = new OBJ_Wings(gp);
        gp.obj[7].worldX = 30 * gp.tileSize;
        gp.obj[7].worldY = 7 * gp.tileSize;




    }


}
