package tile;

public class Obstacle{

	public int m_x, m_y;
	public int m_width, m_height;
	public boolean collision = false;
	
	Obstacle(int x, int y, int w, int h){
		m_x = x;
		m_y = y;
		m_width = w;
		m_height = h;
	}
	
}
