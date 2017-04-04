package cs301.edu.up.cannonblast;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.SeekBar;

import static java.security.AccessController.getContext;

/**
 * @author Javier Resop
 * @version April 2017
 */

public class Cannonball implements Animator
{
	private final float GRAVITY = 9.8f;
	private int VELOCITY = 100;
	private final int MAX_Y = 1500;
	private int count = 0;
	private double angle = 50 * 3.14 / 180;
	private	int newX = 0;
	private	int newY = 0;
	private int oldX = 250;
	private int oldY = 1150;
	private boolean fired = false;

	//sets the interval
	@Override
	public int interval()
	{
		return 30;
	}

	//makes sure the bkgd is sky blue
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

	//draws the cannonball in motion
	@Override
	public void tick(Canvas canvas)
	{
		if(fired) {
			count++;

			if(count > 1)
			{
				oldX = newX;
				oldY = newY;
			}
			
			newX = (int)(VELOCITY * Math.cos(angle) * count);
			newY = (int)(-((VELOCITY * Math.sin(angle) * count) - (0.5 * GRAVITY * count * count)));

			// Draw the ball in the correct position.
			Paint redPaint = new Paint();
			redPaint.setColor(Color.GRAY);
			canvas.drawCircle(250 + newX, MAX_Y-350 + newY, 60, redPaint);
			if(newY >= MAX_Y){fired = false;}
		}
	}

	//don't deal with touches
	@Override
	public void onTouch(MotionEvent event)
	{

	}

	//sets the new angle
	public void setAngle(double newAngle)
	{
		this.angle = newAngle;
	}

	//makes a new cannonball shot
	public void fire()
	{
		fired=true;
		count=0;
	}

	//set the velocity
	public void setVelocity(int newVelocity)
	{
		this.VELOCITY = newVelocity;
	}

	//gets the new x location of the cannonball
	public int getNewX()
	{
		return newX;
	}

	//gets the new y location of the cannonball
	public int getNewY()
	{

		return newY;
	}

	//gets the old x position of the cannonball
	public int getOldX()
	{
		return oldX;
	}

	//gets the old  position of the cannonball
	public int getOldY()
	{
		return oldY;
	}
}
