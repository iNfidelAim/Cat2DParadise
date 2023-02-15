package org.game.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {

    public OBJ_Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/paradisedoor.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

}