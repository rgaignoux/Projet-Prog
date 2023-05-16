package entity;

import java.awt.image.BufferedImage;

public class Enemy extends Entity{
	
	int damage;
	
	public Enemy(int p_x, int p_y, int speed, BufferedImage image, int damage) {
		m_x = p_x;
		m_y = p_x;
		m_speed = speed;
		m_idleImage = image;
		this.damage = damage;
	}
}
