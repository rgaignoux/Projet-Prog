package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

/**
 * D�fintition du comportement d'un joueur
 *
 */
public class Player extends EntityMovable {

	KeyHandler m_keyH;
	boolean usArc = false;
	boolean usSword = true;

	/**
	 * Constructeur de Player
	 * 
	 * @param a_gp   GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches
	 */
	public Player(GamePanel a_gp, KeyHandler a_keyH) {
		this.m_gp = a_gp;
		this.m_keyH = a_keyH;
		this.setDefaultValues();
		this.getPlayerImage();
	}
	
	public int[] getPostion() {
		int[] p = {m_x, m_y};
		return p;
	}

	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_x = 100;
		m_y = 100;
		m_speed = 4;
		m_pv = 10;
	}

	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getPlayerImage() {
		// gestion des expections
		try {
			if(usSword) {
				m_idleImage = ImageIO.read(getClass().getResource("/player/heroEpee.png"));
			} else {
				m_idleImage = ImageIO.read(getClass().getResource("/player/heroArc.png"));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mise � jour des donn�es du joueur
	 */
	public void update() {
		deplacement(m_keyH);
		getPlayerImage();
		weapon(m_keyH);
	}

	public void deplacement(KeyHandler k) {
		int code = k.keyP;
		System.out.println(code);
		if (!Collision.collisionObstacles(m_gp, this)) {
			if (code == 90) {
				this.goUp();
			}
			if (code == 81) {
				this.goLeft();
			}
			if (code == 83) {
				this.goDown();
			}
			if (code == 68) {
				this.goRight();
			}
		}
	}
	
	public void weapon(KeyHandler k) {
		int code = k.keyP;
		if (code == 49) {
			this.usSword = true;
			this.usArc = false;
		}
		if (code == 50) {
			this.usSword = false;
			this.usArc = true;
		}
	}

	/**
	 * Affichage du l'image du joueur dans la fen�tre du jeu
	 * 
	 * @param a_g2 Graphics2D
	 */
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et
		// de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}

}
