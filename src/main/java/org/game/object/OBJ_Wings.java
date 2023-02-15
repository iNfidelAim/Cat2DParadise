package org.game.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Wings extends SuperObject{

    public OBJ_Wings() {
        name = "Wings";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/wings.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
