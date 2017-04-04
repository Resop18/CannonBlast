package cs301.edu.up.cannonblast;

import android.content.Context;
import android.graphics.Point;
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
	private TextView angleText;
	private TextView velocityText;
	private Cannon testAnim;
	private long[] pattern = {0,50,100,200};

	/**
	 * creates an AnimationCanvas containing a Cannon
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cannon_main);

		// Create an animation canvas and place it in the main layout
		testAnim = new Cannon();
		AnimationCanvas myCanvas = new AnimationCanvas(this, testAnim);
		LinearLayout mainLayout = (LinearLayout) this.findViewById(R.id.topLevelLayout);
		mainLayout.addView(myCanvas);

		fire = (Button) findViewById(R.id.FIRE);
		fire.setOnClickListener(this);

		angle = (SeekBar) findViewById(R.id.Angle);
		angle.setOnSeekBarChangeListener(this);

		velocity = (SeekBar) findViewById(R.id.Velocity);
		velocity.setOnSeekBarChangeListener(this);

		angleText = (TextView) findViewById(R.id.AngleText);
		velocityText = (TextView) findViewById(R.id.VelocityText);

		vb = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
	}

	@Override
	public void onClick(View v)
	{
		vb.vibrate(pattern, -1);
		testAnim.fire();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
	{
		angleText.setText("" + angle.getProgress() +"°");
		velocityText.setText("" + velocity.getProgress() + "mph");
		testAnim.setAngle(angle.getProgress());
		testAnim.setVelocity(velocity.getProgress());
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
