package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import map.Labyrinthe;
import utils.Collision;

/**
 * D�fintition du comportement d'un joueur
 *
 */
public class Player extends EntityMovable {

	KeyHandler m_keyH;
	boolean usArc = false;
	boolean usSword = true;
	public List<Boolean> life;
	public boolean dead = false;
	public int pv_max = 10;
	public BufferedImage m_NoPvImage;
	public BufferedImage m_PVImage;

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
		this.getPvImage();
		life = new ArrayList<>();
		updatePv();
	}

	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_x = 100;
		m_y = 100;
		m_speed = 3;
		m_pv = 10;
	}

	protected void copyPosition(Player p) {
		m_x = p.m_x;
		m_y = p.m_y;
	}

	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getPlayerImage() {
		// gestion des expections
		try {
			if (usSword) {
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
		lifeUpdate();
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

	public void deplacement(KeyHandler k) {
		int code = k.keyP;
		Player positionFuture = new Player(this.m_gp, this.m_keyH);
		positionFuture.copyPosition(this);
		
		// Changement de salle
		if(this.m_x > m_gp.SCREEN_WIDTH - m_gp.TILE_SIZE && this.m_y > m_gp.SCREEN_HEIGHT - m_gp.TILE_SIZE) {
			this.m_x = 5;
			this.m_y = 5;
			this.m_gp.m_tileM.m_mapTileNum = Labyrinthe.generateMaze(this.m_gp.m_tileM.m_mapTileNum);
			this.m_gp.resetSalle();
		}

		if (code == 90 && positionFuture.m_y > 0) {
			positionFuture.goUp();
			if (!Collision.collisionObstacles(m_gp, positionFuture))
				this.goUp();
		}
		if (code == 81 && positionFuture.m_x > 0) {
			positionFuture.goLeft();
			if (!Collision.collisionObstacles(m_gp, positionFuture))
				this.goLeft();
		}
		if (code == 83 && positionFuture.m_y < m_gp.SCREEN_HEIGHT) {
			positionFuture.goDown();
			if (!Collision.collisionObstacles(m_gp, positionFuture))
				this.goDown();
		}
		if (code == 68 && positionFuture.m_x < m_gp.SCREEN_WIDTH) {
			positionFuture.goRight();
			if (!Collision.collisionObstacles(m_gp, positionFuture))
				this.goRight();
		}
	}

	/**
	 * Affichage du l'image du joueur dans la fen tre du jeu
	 * 
	 * @param a_g2 Graphics2D
	 */
	public void draw(Graphics2D a_g2) {
		// r cup re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn es x et y, et
		// de taille tileSize (16x16) sans chelle, et 48x48 avec chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
		drawPv(a_g2);
	}

	public void updatePv() {
		for (int i = 0; i < pv_max; i++) {
			life.add(i < m_pv);
		}
		if (m_pv == 0) {
			dead = true;
		}
	}

	public void getPvImage() {
		// gestion des expections
		try {
			m_PVImage = ImageIO.read(getClass().getResource("/player/heart.png"));
			m_NoPvImage = ImageIO.read(getClass().getResource("/player/speedboots.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void lifeUpdate() {
		for (Entity e : m_gp.listeEntity) {
			if (e.getClass().getName().equals("Spider") && Collision.collisionEntity(this, e)) {
				m_pv--;
				this.updatePv();
			}
			if (e.getClass().getName().equals("Heart") && Collision.collisionEntity(this, e) && m_pv < pv_max) {
				m_pv++;
				this.updatePv();
			}
		}
	}

	public void drawPv(Graphics2D a_g2) {
		BufferedImage l_image1 = m_PVImage;
		BufferedImage l_image2 = m_NoPvImage;
		for (int i = 0; i < life.size(); i++) {
			if (life.get(i)) {
				a_g2.drawImage(l_image1, i * 25 + 10, 540, 25, 25, null);
			} else {
				a_g2.drawImage(l_image2, i * 25 + 10, 540, 25, 25, null);
			}
		}

	}

}
