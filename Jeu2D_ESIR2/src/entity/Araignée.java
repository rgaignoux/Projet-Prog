package entity;

public class Araignee extends Enemy{
	
	int speed = 10;
	
	
	public Araignee (int p_x, int p_y) {
		super(p_x, p_y, this.speed, this.image, this.damage);
	}

}
