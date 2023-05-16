package tile;

import java.awt.image.BufferedImage;

/**
 * 
 * Element graphique de la carte
 */
public class Tile {
	public BufferedImage m_image;		//image
	public boolean collision;
	public int m_x, m_y;
	public int width, height;
	
	Tile(boolean collision){
		this.collision = collision;
	}
}
