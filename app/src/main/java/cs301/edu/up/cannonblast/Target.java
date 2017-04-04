package cs301.edu.up.cannonblast;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author Javier Resop
 * @version April 2017
 */

public class Target implements Animator
{
	private boolean ring1Hit = false;
	private int posX, posY, radius;

	public Target(int initPosX, int initPosY, int initRadius)
	{
		posX = initPosX;
		posY = initPosY;
		radius = initRadius;
	}

	//sets the interval
	@Override
	public int interval()
	{
		return 30;
	}

	//sets the background
	@Override
	public int backgroundColor()
	{
		return 0;
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

	//update the target if it is hit
	@Override
	public void tick(Canvas canvas)
	{
		Paint ring1 = new Paint();
		Paint ring2 = new Paint();
		Paint ring1Hit = new Paint();
		Paint ring2Hit = new Paint();

		ring1.setColor(0xFFA89FF3);
		ring2.setColor(0xFF0FFF9F);
		ring1Hit.setColor(0xFF57600C);
		ring2Hit.setColor(0xFFF00060);

		if(this.ring1Hit)
		{
			canvas.drawCircle(posX,posY,radius,ring1Hit);
			canvas.drawCircle(posX,posY,radius - 25,ring2Hit);
			canvas.drawCircle(posX,posY,radius - 50,ring1Hit);
			canvas.drawCircle(posX,posY,radius - 75,ring2Hit);
		}
		else
		{
			canvas.drawCircle(posX,posY,radius,ring2);
			canvas.drawCircle(posX,posY,radius - 25,ring1);
			canvas.drawCircle(posX,posY,radius - 50,ring2);
			canvas.drawCircle(posX,posY,radius - 75,ring1);
		}
	}

	//do nothing
	@Override
	public void onTouch(MotionEvent event)
	{

	}

	//check to see if the cannonball is inside
	public boolean containsPoint(int x, int y) {
		//Calculate the distance between this point and the center
		int xDist = Math.abs(x - this.posX);
		int yDist = Math.abs(y - this.posY);
		int dist = (int)Math.sqrt(xDist*xDist + yDist*yDist);  //Thanks, Pythagoras :)
		if(dist < this.radius)
		{
			Log.i("dist1","" + dist);
		}

		return dist < this.radius + 10;
	}

	//sets if the the target is hit
	public void setRing1Hit(boolean ring1Hit)
	{
		this.ring1Hit = ring1Hit;
	}

}