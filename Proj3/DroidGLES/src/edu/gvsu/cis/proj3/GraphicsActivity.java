package edu.gvsu.cis.proj3;

import edu.gvsu.cis.proj3.R;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Activity to run our program.  Based on Dr. Dulimarta's code with 
 * a few modifications
 *
 */
public class GraphicsActivity extends Activity {
    private GLView mView;
    private ToggleButton lighting, accel, anim, cake;
    private RadioGroup group;
    private RadioButton moveLight, moveSphere, moveTable;
    private SensorManager sm;
    private Display myDisplay;
    private TransformationParams par;
    private GLRenderer render;
    private boolean useSensor;
    private static TextView scoreText;
    private static Activity thisActivity;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = this;
        // remove the title and status bars to make it full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
        par = new TransformationParams();
        par.eyeX = 0f;
        par.eyeY = 0f;
        par.eyeZ = 25f;
        par.litePos[0] = 0f;
        par.litePos[1] = 0f;
        par.litePos[2] = 15f;
        
        mView = new GLView(this);
        mView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
      
        
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        myDisplay = getWindowManager().getDefaultDisplay();
        setContentView(R.layout.main);

        scoreText = (TextView) findViewById(R.id.score);
        
        group = (RadioGroup) findViewById(R.id.radiogroup);
        lighting = (ToggleButton) findViewById(R.id.tblight);
        lighting.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				render.setLighting(isChecked);
			}
		});
        accel = (ToggleButton) findViewById(R.id.tbsensor);
        accel.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                useSensor = isChecked;
            }
        });
        //technique to replace the dummy view as shown in the example
        View dummy = (View) findViewById(R.id.dummy);
        ViewGroup top = (ViewGroup) dummy.getParent();
        
        mView.setLayoutParams(dummy.getLayoutParams());
        int idx = top.indexOfChild(dummy);
        top.removeViewAt(idx);
        top.addView (mView, idx);
    }

    
	@Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
        sm.unregisterListener(sensory);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mView.onResume();
        for (Sensor s : sm.getSensorList(Sensor.TYPE_ACCELEROMETER))
            sm.registerListener(sensory, s, SensorManager.SENSOR_DELAY_UI);
    }
   
    /* save and restore the XY-translation, so after orientation switch,
     * the box stay at the same spot */ 
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        par = savedInstanceState.getParcelable("param");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("param", par);
    }

    public class GLView extends GLSurfaceView {
        double prevDist, currDist;
        
        public GLView(Context context) {
            super(context);
            render = new GLRenderer(context, par);
            setRenderer(render);
            setRenderMode(RENDERMODE_CONTINUOUSLY);
        }

        @Override
        public boolean onTouchEvent(final MotionEvent event) {
            queueEvent(new Runnable() {

                @Override
                public void run() {
                    if (event.getPointerCount() == 1) {
                    	render.doSwipe(event, group.getCheckedRadioButtonId());
                    }
                    else if (event.getPointerCount() >= 2) {
                    	render.doPinch(event, group.getCheckedRadioButtonId());
                    }
                    /* when the current render mode is "WHEN_DIRTY", the
                     * following call to requestRender() is necessary */
                    requestRender();
                }
            });
            return true;
        }
        
    }

    private SensorEventListener sensory = new SensorEventListener() {
        private float[] grav = new float[3];
        final float ALPHA = 0.5f;
        
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
                return;
            if (useSensor)
                for (int k = 0; k < 3; k++)
                    grav[k] = ALPHA * grav[k] + (1 - ALPHA) * event.values[k];
            else
                for (int k = 0; k < 3; k++)
                    grav[k] = 0;
            render.doTilt(grav, event.timestamp, myDisplay.getOrientation());
        }
        
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
            
        }
    };
    /**
     * Ugly hack so that the renderer can update the score
     * @param text
     */
    public static void changeScoreText(final String text){
    	thisActivity.runOnUiThread(new Runnable(){
    		public void run(){
    			scoreText.setText(text);
    		}
    	});
    }
}