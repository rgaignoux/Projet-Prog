package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import utils.Collision;

public class Arrow extends Player {

	public Arrow(GamePanel a_gp, KeyHandler a_keyH) {
		super(a_gp, a_keyH);
	}

	int p_x_arrow = m_x;
	int p_y_arrow = m_y;
	int arrow_speed = 6;
	int x_direction = 0;
	int y_direction = 0;
	boolean killArrow = false;

	public void update() {
		deplacement(m_keyH);
		getArrowImage();
	}

	public void deplacement(KeyHandler k) {
		int code = k.keyP;
		System.out.println(code);
		if (!Collision.collisionObstacles(m_gp, this) /*|| ! Collision.collisionEntity(this, )*/) {
			if (code == 38) {
				p_x_arrow -= arrow_speed;
				x_direction = -1;
				y_direction = 0;
			}
			if (code == 37) {
				p_y_arrow += arrow_speed;
				y_direction = 1;
				x_direction = 0;
			}
			if (code == 40) {
				p_x_arrow += arrow_speed;
				x_direction = 1;
				y_direction = 0;
			}
			if (code == 39) {
				p_y_arrow -= arrow_speed;
				y_direction = -1;
				x_direction = 0;
			}
		}
		killArrow = true;
	}

	public void getArrowImage() {
		// gestion des expections
		try {
			if (x_direction == 1) {
				m_idleImage = ImageIO.read(getClass().getResource("/fleche/right.png"));
			} else if (x_direction == -1) {
				m_idleImage = ImageIO.read(getClass().getResource("/fleche/left.png"));
			} else if (y_direction == 1) {
				m_idleImage = ImageIO.read(getClass().getResource("/fleche/up.png"));
			} else if (y_direction == -1) {
				m_idleImage = ImageIO.read(getClass().getResource("/fleche/down.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
