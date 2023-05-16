package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.Collision;

public class Spider extends Enemy {

	private int x_direction; // -1 ; 0 ; 1
	private int y_direction; // -1 ; 0 ; 1

	public Spider(GamePanel a_gp, int p_x, int p_y) {
		super(a_gp, p_x, p_y, 2, 1, 3);
		Random rand = new Random();
		int directionRandom = rand.nextInt(4);
		
		if(directionRandom == 0) {
			this.x_direction = 0;
			this.y_direction = 1;
		}
		
		if(directionRandom == 1) {
			this.x_direction = 0;
			this.y_direction = -1;
		}
		
		if(directionRandom == 2) {
			this.x_direction = 1;
			this.y_direction = 0;
		}
		
		if(directionRandom == 3) {
			this.x_direction = -1;
			this.y_direction = 0;
		}
		
		
		
		m_width = 27;
		m_height = 27;
		this.getSpiderImage();
	}

	public void update() {
		// L'araignée change de direction a chaque collision
		if(Collision.collisionObstacles(m_gp, this)) {
			x_direction = -x_direction;
			y_direction = -y_direction;
		}
		
		if (x_direction == -1) {
			this.goLeft();
		}
		if (x_direction == 1) {
			this.goRight();
		}
		if (y_direction == -1) {
			this.goDown();
		}
		if (y_direction == 1) {
			this.goUp();
		}

	}

	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getSpiderImage() {
		// gestion des expections
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/enemy/spider.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D a_g2) {
		BufferedImage l_image = m_idleImage;
		a_g2.drawImage(l_image, m_x, m_y, m_width, m_height, null);
	}
}
