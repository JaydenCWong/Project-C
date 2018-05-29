import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Archer extends Sprite{
	protected double velocityX = 000;        	//PIXELS PER SECOND
	protected double velocityY = 0;          	//PIXELS PER SECOND
	protected double reloadTime = 0;
	private int currentAngle = 0;
	private int range;
	private double damage;
	private double rateOfFire;
	private double speed;
	private double health;
	
	
	public Archer(double currentX, double currentY, int floor){
		
		try {
			this.defaultImage = ImageIO.read(new File("res/simple-sprite.png"));
			this.IMAGE_HEIGHT = this.defaultImage.getHeight(null);
			this.IMAGE_WIDTH = this.defaultImage.getWidth(null);
		}
		catch (IOException e) {
			System.out.println(e.toString());
		}
		if (floor == 1){
			this.range = 300;
			this.damage = 1;
			this.rateOfFire = 1;
			this.speed = 0.1;
			this.health = 1;
		}
		else if (floor == 2){
			this.range = 400;
			this.damage = 1.5;
			this.rateOfFire = 1;
			this.speed = 0.1;
			this.health = 2;
		}
		else if (floor == 3){
			this.range = 450;
			this.damage = 1.5;
			this.rateOfFire = 1.5;
			this.speed = 0.1;
			this.health = 3;
		}
		else if (floor == 4){
			this.range = 450;
			this.damage = 2;
			this.rateOfFire = 1.5;
			this.speed = 0.1;
			this.health = 5;
			
		}
		
	}
	public int getCurrentAngle() {
		return currentAngle;
	}
	public int getRange() {
		return range;
	}
	public double getDamage() {
		return damage;
	}
	public double getRateOfFire() {
		return rateOfFire;
	}
	public double getSpeed() {
		return speed;
	}
	public double getHealth() {
		return health;
	}
	@Override
	public void update(KeyboardInput keyboard, long actual_delta_time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getMinX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaxX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMinY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaxY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMinX(double currentX) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMinY(double currentY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBarriers(ArrayList<Rectangle> barriers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSprites(ArrayList<Sprite> staticSprites) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setKeyboard(KeyboardInput keyboard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkCollisionWithSprites(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkCollisionWithBarrier(double x, double y) {
		boolean isColliding = false;
		
		for (Rectangle barrier : barriers) {			
			//colliding in x dimension?	
			if ( !( (x + this.IMAGE_WIDTH) < barrier.getMinX() || x > barrier.getMaxX())) {			
				//colliding in y dimension?	
				if ( !( (y + this.IMAGE_HEIGHT) < barrier.getMinY() || y > barrier.getMaxY())) {								
					isColliding = true;
					break;
				}
			}
		}
		
		return isColliding;
	}
	public void shoot() {
		
		if (reloadTime <= 0) {
			double currentVelocity = Math.sqrt((velocityX * velocityX) + (velocityY * velocityY));
			double bulletVelocity = 500; // + currentVelocity;
			double ratio = (bulletVelocity / currentVelocity);
//			 = ratio * velocityX + velocityX;
//			double bulletVelocityY = ratio * velocityY + velocityY;
			double angleInRadians = Math.toRadians(currentAngle);
			double bulletVelocityX = Math.cos(angleInRadians) * bulletVelocity + velocityX;
			double bulletVelocityY = Math.sin(angleInRadians) * bulletVelocity + velocityY;
			
			double bulletCurrentX = (this.currentX + (this.IMAGE_WIDTH / 2));
			double bulletCurrentY = (this.currentY + (this.IMAGE_HEIGHT / 2));

			BulletSprite bullet = new BulletSprite(bulletCurrentX, bulletCurrentY, bulletVelocityX, bulletVelocityY);
			System.out.println("shoot!");
			sprites.add(bullet);
			AudioPlayer.playAsynchronous("res/missile.wav");
			
			reloadTime = 100;
			
		}
	}
}
