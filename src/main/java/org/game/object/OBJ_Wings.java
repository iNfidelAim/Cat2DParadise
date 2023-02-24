package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;


public class OBJ_Wings extends Entity {

    public OBJ_Wings(GamePanel gp) {

        super(gp);
        name = "Wings";
        down1 = setup("/objects/wings");

    }
}
