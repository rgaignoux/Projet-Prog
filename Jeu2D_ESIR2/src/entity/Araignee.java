package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Araignee extends Enemy{
	
	private static BufferedImage image_araignee;
	private int[] direction = new int[1];
	
	public Araignee (GamePanel a_gp, int p_x, int p_y) {
		super(a_gp, p_x, p_y, 10, image_araignee, 1, 3);
		Random rn = new Random();
		while (direction[0] == 0 && direction[1] ==0) {
			direction[0] = rn.nextInt(1) - 1;
			direction[1] = rn.nextInt(1) - 1;
		}
	}
	
	public void update() {
	}
	
	
	
	public void getAraigneeImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/player/superhero.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D a_g2) {
		BufferedImage l_image = m_idleImage;
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
	
	public void deplacement() {
	}

}
