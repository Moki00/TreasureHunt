package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Chest extends SuperObject {

	public Obj_Chest() {

		name = "Chest";

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}