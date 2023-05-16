package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class MobADefinir extends Enemy {

	private int x_direction; // -1 ; 0 ; 1
	private int y_direction; // -1 ; 0 ; 1
	private int tick;

	public MobADefinir(GamePanel a_gp, int p_x, int p_y) {
		super(a_gp, p_x, p_y, 1, 1, 3);
		Random rn = new Random();
		this.x_direction = 0;
		this.y_direction = 0;
		this.tick = 0;
		this.getAraigneeImage();
	}
	public void update() {
		// L'araignée change de direction tous les 20 ticks
		if(tick == 20) {
			Random rand = new Random(); 
			x_direction = rand.nextInt(3) - 1; 
			y_direction = rand.nextInt(3) - 1; 
			tick = 0;
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
		tick++;
	}
	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getAraigneeImage() {
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
