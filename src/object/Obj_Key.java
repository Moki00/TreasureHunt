package object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The key to open doors
 * 
 * @author Moki_21_10
 *
 */
public class Obj_Key extends SuperObject {

	public Obj_Key() {

		name = "Key";

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// unique solid area for each item
//		solidArea.x = 5;

	}

}
