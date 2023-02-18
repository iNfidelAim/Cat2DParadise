package org.game.object;

import org.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Wings extends SuperObject{

    GamePanel gp;
    public OBJ_Wings(GamePanel gp) {
        this.gp = gp;
        name = "Wings";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/wings.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
