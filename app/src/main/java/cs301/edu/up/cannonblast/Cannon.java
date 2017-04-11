package cs301.edu.up.cannonblast;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Vector;

/**
 * @author Javier Resop
 * @version April 2017
 */

public class Cannon implements Animator
{
	private float angle = 50;
	private Vector<Cannonball> balls;
	private Cannonball cannonball1;
	private Cannonball cannonball2;
	private Cannonball cannonball3;
	private Cannonball cannonball4;
	private Cannonball cannonball5;
	private Cannonball cannonball6;
	private Cannonball cannonball7;
	private Cannonball cannonball8;
	private Cannonball cannonball9;
	private Cannonball cannonball10;
	private Vector<Target> targets;
	private Target target1;
	private Target target2;
	private Target target3;
	private Target target4;
	private Target target5;
	private boolean fire = false;
	private int time = 0;
	private int width;
	private MediaPlayer se;

	/**
	 * ctor for cannon
	 *
	 * creates a cannonball and targets
	 */
	public Cannon(int maxY, int maxX, MediaPlayer sound)
	{
		se = sound;
		width = maxX;
		targets = new Vector<Target>();
		balls = new Vector<Cannonball>();
		cannonball1 = new Cannonball(maxY);
		cannonball2 = new Cannonball(maxY);
		cannonball3 = new Cannonball(maxY);
		cannonball4 = new Cannonball(maxY);
		cannonball5 = new Cannonball(maxY);
		cannonball6 = new Cannonball(maxY);
		cannonball7 = new Cannonball(maxY);
		cannonball8 = new Cannonball(maxY);
		cannonball9 = new Cannonball(maxY);
		cannonball10 = new Cannonball(maxY);
		balls.add(cannonball1);
		balls.add(cannonball2);
		balls.add(cannonball3);
		balls.add(cannonball4);
		balls.add(cannonball5);
		balls.add(cannonball6);
		balls.add(cannonball7);
		balls.add(cannonball8);
		balls.add(cannonball9);
		balls.add(cannonball10);
		target1 = new Target(700,800,100, true);
		target2 = new Target(1000,1000,100, false);
		target3 = new Target(1300,600,100, true);
		target4 = new Target(1700,750,100, false);
		targets.add(target1);
		targets.add(target2);
		targets.add(target3);
		targets.add(target4);
	}

	//the interval
	@Override
	public int interval()
	{
		return 30;
	}

	//makes a sky blue background
	@Override
	public int backgroundColor()
	{
		return Color.rgb(135, 206, 235);
	}

	//do nothing
	@Override
	public boolean doPause()
	{
		return false;
	}

	//do nothing
	@Override
	public boolean doQuit()
	{
		return false;
	}

	//constantly updates the display calling other animations as well
	@Override
	public void tick(Canvas canvas)
	{
		time++;
		Paint grass =new Paint();
		grass.setColor(Color.GREEN);
		canvas.drawRect(0,1250, 2048, 1500, grass);
		for(Cannonball cannonball: balls) {
			for (Target tg: targets) {
				for (int x = 250 + cannonball.getOldX(); x <= 250 + cannonball.getNewX(); x++) {
					for (int y = (1150 + cannonball.getOldY()); y <= (1150 + cannonball.getNewY()); y++) {

						if (tg.containsPoint(x, y)) {
							tg.setRing1Hit(true);
							se.start();
							break;
						}
					}
					break;
				}

			}
		}
		for (Target tg:targets)
		{
			tg.tick(canvas);
		}

		Paint cannon = new Paint();
		Paint base	 = new Paint();
		base.setColor(Color.BLACK);
		cannon.setColor(0xFF101010);

		if(fire)
		{
			Paint smoke = new Paint();
			smoke.setColor(Color.argb(200,200,200,200));
			canvas.drawCircle(300,1150,60,smoke);
			fire = false;
		}
		cannonball1.tick(canvas);
		cannonball2.tick(canvas);
		cannonball3.tick(canvas);
		cannonball4.tick(canvas);
		cannonball5.tick(canvas);
		cannonball6.tick(canvas);
		cannonball7.tick(canvas);
		cannonball8.tick(canvas);
		cannonball9.tick(canvas);
		cannonball10.tick(canvas);


		canvas.save();
		canvas.rotate(-angle+90,100,1200);
		canvas.drawRect(0,1000,300,1400,cannon);
		canvas.restore();
		canvas.drawRect(0,1250,400,1500,base);
	}


	//not dealing with touches
	@Override
	public void onTouch(MotionEvent event)
	{

	}

	//change the angle
	public void setAngle(double newAngle)
	{
		this.angle = (float)newAngle;
		for (Cannonball cannonball: balls) {
			cannonball.setAngle(newAngle * 3.14 / 180);
		}

	}

	//change the velocity
	public void setVelocity(int newVelocity)
	{
		for (Cannonball cannonball: balls) {
			cannonball.setVelocity(newVelocity);
		}
	}

	//causes smoke to happen as well as fires the cannonball
	public void fire()
	{
		fire = true;
		for (Cannonball cannonball: balls) {
			if (!cannonball.getFired() && cannonball.getFire()){
				cannonball.fire();
				break;
			}
		}
	}

	public void setGravity(double gravity)
	{
		for (Cannonball ball:balls) {
			ball.setGravity(gravity);
		}
	}
}
