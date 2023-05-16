package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Spider extends Enemy {

	private int x_direction; // -1 ; 0 ; 1
	private int y_direction; // -1 ; 0 ; 1

	public Spider(GamePanel a_gp, int p_x, int p_y) {
		super(a_gp, p_x, p_y, 1, 1, 3);
		Random rn = new Random();
		this.x_direction = 0;
		this.y_direction = 0;
		this.getSpiderImage();
	}
	public void update() {
		// L'araignée change de direction a chaque collision
		
		
		if(Collision.collisionObstacles(m_gp, this)) {
			Random rand = new Random(); 
			x_direction = rand.nextInt(3) - 1; 
			y_direction = rand.nextInt(3) - 1; 
		}
		
		
		
		if(x_direction == -1) {
			this.goLeft();
		}
		if(x_direction == 1) {
			this.goRight();
		}
		if(y_direction == -1) {
			this.goDown();
		}
		if(y_direction == 1) {
			this.goUp();
		}
	}
	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getSpiderImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/enemy/spider.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D a_g2) {
		BufferedImage l_image = m_idleImage;
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
}
