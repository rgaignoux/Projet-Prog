package entity;

import main.GamePanel;

public abstract class EntityMovable extends Entity{

	public int m_speed;
	public int m_pv;
	public GamePanel m_gp;
	public int m_damage;
	
	public void goUp(){
		m_y -= m_speed;
	}
	
	public void goDown() {
		m_y += m_speed;
	}
	
	public void goRight() {
		m_x += m_speed;
	}
	
	public void goLeft() {
		m_x -= m_speed;
	}
}
