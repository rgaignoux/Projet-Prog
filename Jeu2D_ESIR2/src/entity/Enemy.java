package entity;

import java.awt.image.BufferedImage;


import main.GamePanel;

public class Enemy extends EntityMovable{
	
	public Enemy(GamePanel a_gp, int p_x, int p_y, int speed, BufferedImage image, int damage, int pv) {
		m_gp = a_gp;
		m_x = p_x;
		m_y = p_x;
		m_speed = speed;
		m_damage = damage;
		m_idleImage = image;
		m_pv = pv;
	}
}
