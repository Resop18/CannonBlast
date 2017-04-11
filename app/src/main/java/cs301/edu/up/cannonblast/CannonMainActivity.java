package cs301.edu.up.cannonblast;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;




/**
 * CannonMainActivity
 * 
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object
 * 
 * @author Andrew Nuxoll
 * @author Javier Resop
 * @version September 2012
 * 
 */
public class CannonMainActivity extends Activity implements View.OnClickListener,
		SeekBar.OnSeekBarChangeListener {

	private Button fire;
	private Vibrator vb;
	private SeekBar angle;
	private SeekBar velocity;
	private SeekBar gravity;
	private TextView angleText;
	private TextView velocityText;
	private TextView gravityText;
	private TextView shots;
	private Cannon testAnim;
	private int width, height;
	private int numShots = 10;
	private long[] pattern = {0,50,100,200};
	private MediaPlayer bang;
	private MediaPlayer tick;
	private MediaPlayer pop;

	/**
	 * creates an AnimationCanvas containing a Cannon
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cannon_main);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;

		bang = MediaPlayer.create(this, R.raw.cannon2);
		tick = MediaPlayer.create(this, R.raw.tick);
		pop = MediaPlayer.create(this, R.raw.pop);

		// Create an animation canvas and place it in the main layout
		testAnim = new Cannon(height, width, pop);
		AnimationCanvas myCanvas = new AnimationCanvas(this, testAnim);
		LinearLayout mainLayout = (LinearLayout) this.findViewById(R.id.topLevelLayout);
		mainLayout.addView(myCanvas);

		fire = (Button) findViewById(R.id.FIRE);
		fire.setOnClickListener(this);

		angle = (SeekBar) findViewById(R.id.Angle);
		angle.setOnSeekBarChangeListener(this);

		gravity = (SeekBar) findViewById(R.id.GavitySeekBar);
		gravity.setOnSeekBarChangeListener(this);

		velocity = (SeekBar) findViewById(R.id.Velocity);
		velocity.setOnSeekBarChangeListener(this);

		angleText = (TextView) findViewById(R.id.AngleText);
		velocityText = (TextView) findViewById(R.id.VelocityText);
		shots = (TextView) findViewById(R.id.Balls);
		gravityText = (TextView) findViewById(R.id.GravityText);

		vb = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
	}

	@Override
	public void onClick(View v)
	{
		if(numShots > 0) {
			bang.start();
			vb.vibrate(pattern, -1);
			testAnim.fire();
			numShots--;
		}
		else{tick.start();}
		shots.setText("Shots: " + numShots);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
	{
		angleText.setText("" + angle.getProgress() +"°");
		velocityText.setText("" + velocity.getProgress() + "mph");

		testAnim.setAngle(angle.getProgress());
		testAnim.setVelocity(velocity.getProgress());
		if(gravity.getProgress()==0) {
			testAnim.setGravity(0);
			gravityText.setText("" + 0);
		}
		else{
			testAnim.setGravity(.1 * gravity.getProgress());
			gravityText.setText(String.format("%.1f", (.1 * gravity.getProgress())));
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar)
	{

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar)
	{

	}
}
