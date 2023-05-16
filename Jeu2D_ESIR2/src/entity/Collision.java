package entity;

import java.awt.Rectangle;

import main.GamePanel;
import tile.TileManager;

import tile.Tile;

public class Collision {
	
	public static Rectangle getBounds(Entity e) {
        return new Rectangle(e.m_x, e.m_y, e.m_width, e.m_height);
    }
	
	public static Rectangle getBounds(Tile t) {
        return new Rectangle(t.m_x, t.m_y, t.width, t.height);
    }
	
	/**
	 * Vérifie si une entity est en collision avec un obstacle
	 * @param e une entity (héros, enemy, ...)
	 * @param t un obstacle
	 * @return si l'entity est en collision avec la tile
	 */
	public static boolean collisionObstacle(Entity e, Tile t) {
		Rectangle r_entity = getBounds(e);
		Rectangle r_obstacle = getBounds(t);
		return r_entity.intersects(r_obstacle);
	}
	
	/**
	 * Vérifie si une entity est en collision avec un obstacle parmi tous les obstacles
	 * @param panel le GamePanel
	 * @param e une entity (héros, enemy, ...)
	 * @return si l'entity est en collision avec n'importe quel obstacle collisionable
	 */
	public static boolean collisionObstacles(GamePanel panel, Entity e) {
		int[][] map_tiles = panel.m_tileM.m_mapTileNum;
		Tile[] tiles = panel.m_tileM.m_tile;
		for(int i=0; i < map_tiles.length; i++) {
			for(int j=0; j < map_tiles[i].length; j++) {
				Tile tile = tiles[map_tiles[i][j]];
				if(tile.collision && collisionObstacle(e, tile)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Vérifie si une entity est en collision avec une autre entity
	 * @param e1 une entity (héros, enemy, ...)
	 * @param e2 une entity (héros, enemy, ...)
	 * @return si l'entity est en collision avec l'autre entity
	 */
	public static boolean collisionEntity(Entity e1, Entity e2) {
		Rectangle r_entity_1 = getBounds(e1);
		Rectangle r_entity_2 = getBounds(e2);
		return r_entity_1.intersects(r_entity_2);
	}
}
