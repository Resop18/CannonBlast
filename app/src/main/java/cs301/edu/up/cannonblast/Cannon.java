package cs301.edu.up.cannonblast;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	private Cannonball cannonball;
	private Vector<Target> targets;
	private Target target1;
	private Target target2;
	private Target target3;
	private Target target4;
	private Target target5;
	private boolean fire = false;
	private int time = 0;

	/**
	 * ctor for cannon
	 *
	 * creates a cannonball and targets
	 */
	public Cannon()
	{
		targets = new Vector<Target>();
		cannonball = new Cannonball();
		target1 = new Target(700,800,100);
		target2 = new Target(1000,1000,100);
		target3 = new Target(1300,600,100);
		target4 = new Target(1700,750,100);
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
		for(Target tg: targets) {
			for (int x = 250 + cannonball.getOldX(); x <= 250 + cannonball.getNewX(); x++) {
				for (int y = (1150 + cannonball.getOldY()); y <= (1150 + cannonball.getNewY()); y++) {

					if (tg.containsPoint(x, y)) {
						tg.setRing1Hit(true);
					}
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

		cannonball.tick(canvas);
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
		cannonball.setAngle(newAngle * 3.14 / 180);
	}

	//change the velocity
	public void setVelocity(int newVelocity)
	{
		cannonball.setVelocity(newVelocity);
	}

	//causes smoke to happen as well as fires the cannonball
	public void fire()
	{
		fire = true;
		cannonball.fire();
	}
}
