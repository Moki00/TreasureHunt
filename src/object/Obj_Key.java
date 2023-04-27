package object;

import javax.imageio.ImageIO;

/**
 * The key to open doors
 * 
 * @author Moki_21_10
 *
 */
public class Obj_Key extends SuperObject {

	public Obj_Key() {

		super();
		name = "key";

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
