package org.game.main;

import org.game.entity.NPC_Dog;
import org.game.monsters.MON_TestMonster;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Dog(gp);
        gp.npc[0].worldX = gp.tileSize * 20;
        gp.npc[0].worldY = gp.tileSize * 20;

        gp.npc[1] = new NPC_Dog(gp);
        gp.npc[1].worldX = gp.tileSize * 22;
        gp.npc[1].worldY = gp.tileSize * 22;

        gp.npc[2] = new NPC_Dog(gp);
        gp.npc[2].worldX = gp.tileSize * 25;
        gp.npc[2].worldY = gp.tileSize * 25;


    }

    public void setMonsters() {
        gp.npc[0] = new MON_TestMonster(gp);
        gp.npc[0].worldX = gp.tileSize * 35;
        gp.npc[0].worldY = gp.tileSize * 40;


    }
}
