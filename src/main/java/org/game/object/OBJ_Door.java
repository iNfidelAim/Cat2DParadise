package org.game.object;

import org.game.entity.Entity;
import org.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gp) {

        super(gp);
        name = "Door";
        down1 = setup("/objects/door");
        collision = true;
    }

}
