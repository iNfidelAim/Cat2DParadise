package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {
    public OBJ_Chest(GamePanel gp) {

        super(gp);
        name = "Wings";
        down1 = setup("/objects/wings");
        collision = true;

    }

}
