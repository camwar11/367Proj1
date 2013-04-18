package edu.gvsu.cis.proj3;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import edu.gvsu.cis.proj1.Cake;
import edu.gvsu.cis.proj1.Cube;
import edu.gvsu.cis.proj2.*;
import edu.gvsu.cis.proj3.R;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;

/**
 * Renderer based on the example from Dr. Dulimarta.
 * Modified to draw our scene that is a combination of proj 1 & 2
 * @author Cam
 *
 */
public class GLRenderer implements Renderer {
    private final String TAG = getClass().getName();
    private int score = 0;
    SceneObj table;
    Ball sphere;
    private Cube[] cubes;
    private Texture wood, cakeTxt, metal;
    private Context mCtx;
    private boolean isLandscape, lighting;
    private float ratio;
    private TransformationParams param;
    private float prevX, prevY, prevDist;
    private int scrWidth, scrHeight;
	private  boolean gameOver = false;
    private final static float lightAmb[] = {0.4f, 0.4f, 0.4f, 1f};
    private final static float lightDif[] = {.5f, .5f, .5f, 1f};
    private final static float lightPos[] = {0f, 0f, 10f, 1f};
    private final static float lightDir[] = {0f, 0f, -1f, 1f};
    private final static float materialAmb[] = {0.4f, 0.4f, 0.48f, .5f};
    private final static float materialSpe[] = {0.2f, .2f, .2f, .5f};
    private boolean anim;
    private Cube bgCube;
    public GLRenderer(Context parent, TransformationParams p)
    {
        mCtx = parent;
        anim = false;
        sphere = new Ball(parent);
        cubes = new Cube[4];
        for(int i = 0; i < 4; i++){
        	cubes[i] = new Cube(parent);
        	cubes[i].move();
        }
        
        table = new Table(parent);

        /* initialize transformation variables */
        param = p;
        lighting = true;
        //init background cube
        bgCube = new Cube(parent);
        bgCube.setX(-10);
        bgCube.setY(-10);
        bgCube.size = 1;
        bgCube.width = 70;
        bgCube.length = 30;
        bgCube.moving = false;
        bgCube.localTranslate(0f, .5f, -3f);
    }
    
    @Override
    public synchronized void onDrawFrame(GL10 gl) { 
    	if(gameOver){
    		GraphicsActivity.gameOver();
    		return;
    	}
        // display function
        gl.glClearColor (0, 0, .6f, .5f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL10.GL_COLOR_MATERIAL);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, param.eyeX, param.eyeY, param.eyeZ, /* eye */ 
                param.coa[0], param.coa[1], param.coa[2], /* focal point */
                0, -1f, 0); /* up */
        gl.glColor4f(1.0f, 1.0f, 1.0f, .1f);
        gl.glPushMatrix();
        gl.glTranslatef(param.litePos[0], param.litePos[1], param.litePos[2]);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION, lightDir, 0);
        gl.glPopMatrix();
        
        /////////////////////////////
        gl.glPushMatrix();

        cakeTxt.bind();
        gl.glColor4f(0f, .4f, 0f, .5f);
        //gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        bgCube.draw(null);
        cakeTxt.unbind();
        gl.glPopMatrix();
        /////////////////
        
        if (lighting)
        	gl.glEnable(GL10.GL_LIGHTING);
        else
        	gl.glDisable (GL10.GL_LIGHTING);
        
        //draw the table
        wood.bind();
        gl.glColor4f(.4f, .4f, .4f, 1f);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		table.draw(anim);
		wood.unbind();
        
        metal.bind();
        
        gl.glPushMatrix();
        for(int i = 0; i < 4; i++){
        	cubes[i].draw(anim);
        }
      
        gl.glPopMatrix();
        for(int i = 0; i < 4; i++){
        	if(cubes[i].intersects(sphere.getX(), sphere.getY(), (float)sphere.getSize())){
        		score += cubes[i].move();
        		GraphicsActivity.changeScoreText(""+score);
        		break;
        	}
        }
        sphere.roll(param.ballTrX, param.ballTrY);
        //greyish metal
        gl.glColor4f(.4f, .4f, .4f, 1f);
		sphere.draw(null);
        metal.unbind();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        scrWidth = width;
        scrHeight = height;
        
        gl.glViewport (0, 0, width, height);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        
        /* the following choice of orthographic projection parameters
         * guarantee that the shorter side is at least 2 units long
         */
        isLandscape = width > height;
        ratio = (float) width / height;
        GLU.gluPerspective(gl, 60, ratio, 0.1f, 50.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glShadeModel(GL10.GL_SMOOTH);

        /* Light settings */
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmb, 0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDif, 0);
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_CUTOFF, 25);
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_EXPONENT, 1);
        
        gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, lightAmb,0);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT_AND_DIFFUSE, materialAmb, 0);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, materialSpe, 0);
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, .5f);
        gl.glEnable(GL10.GL_COLOR_MATERIAL);
        
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        //init our textures
        wood = new Texture (mCtx, R.drawable.wood);
        cakeTxt = new Texture (mCtx, R.drawable.grass);
        metal = new Texture (mCtx, R.drawable.metal);
    }
 
    //example modified only move the ball
    public void doSwipe(MotionEvent ev)
    {
        switch (ev.getAction() & MotionEvent.ACTION_MASK)
        {
        case MotionEvent.ACTION_DOWN:
            prevX = ev.getX();
            prevY = ev.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            float tx = .7f * (isLandscape ? ratio : 1.0f) * (ev.getX() - prevX) / scrWidth; //phone is at .5
            float ty = .7f * (isLandscape ? 1.0f : ratio) * (prevY - ev.getY()) / scrHeight; //phone is at .5

            param.ballTrX -= tx; //negative because the controls were backwards
            param.ballTrY -= ty;
                
//            prevX = ev.getX();
  //          prevY = ev.getY();
            break;
        case MotionEvent.ACTION_UP:
        }
    }
    
    public void doPinch (MotionEvent ev)
    {
        //don't handle pinching
    }

    private long tstamp;
    
    //example modified to move the ball
    void doTilt (float[] grav, long timestamp, int orient)
    {
        float len = grav[0] * grav[0] + grav[1] * grav[1];
        if (len >= 10.0) {
        	param.tiltZRot = (float) (Math.atan2(grav[1], grav[0]) * 180.0 / Math.PI);

            switch (orient) {
            case Surface.ROTATION_0:
            	param.tiltZRot = 90 - param.tiltZRot;
                break;
            case Surface.ROTATION_90:
            	param.tiltZRot = -param.tiltZRot;
                break;
            case Surface.ROTATION_180:
            	param.tiltZRot = -param.tiltZRot - 90;
                break;
            case Surface.ROTATION_270:
            	param.tiltZRot = 180 - param.tiltZRot;
            }
        }
        len = grav[1]*grav[1] + grav[2]*grav[2];
        if (len  > 1) {
        	param.tiltXRot = (float)(Math.atan2(grav[2], grav[1]) * 180.0 / Math.PI);
        }
        float gravity_x = 0; 
        float gravity_y = 0;
        
        switch (orient) {
        case Surface.ROTATION_0:
            gravity_x = -grav[0];
            gravity_y = -grav[1];
            break;
        case Surface.ROTATION_90:
            gravity_x = grav[1];
            gravity_y = -grav[0];
            break;
        case Surface.ROTATION_180:
            gravity_x = grav[0];
            gravity_y = grav[1];
            break;
        case Surface.ROTATION_270:
            gravity_x = -grav[1];
            gravity_y = grav[0];
            break;
        }
        if (tstamp != 0) {
            /* event time stamp is in nano second, we want to convert 
             * it to second */
            final float dt = (timestamp - tstamp) / 1e9f;
            param.ballTrX =- 80 * gravity_x * dt * dt*dt;//moves the ball (negative because the controls were backwards)
            param.ballTrY =- 80 * gravity_y * dt * dt*dt;
        }
        tstamp = timestamp;
    	
    }
    void setLighting (boolean onOff)
    {
    	lighting = onOff;
    }
    
    void setAnimation (boolean flag)
    {
        anim = flag;
    }

	public void reset(){
		this.gameOver = false;
		this.score = 0;
		
		//cool little way to limit the game time
		final Runnable r = new Runnable(){
			public void run(){
				GLRenderer.this.gameOver = true;
			}
		};
		Handler h = new Handler();
		h.postDelayed(r, 1000*60); //1 min game
	}
}

