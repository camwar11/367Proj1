package edu.gvsu.cis.proj3;


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
    private final float chaliceSize = 0.5f;

    SceneObj chalice, cake, table;
    private Cube[] cubes;
    private Texture wood, cakeTxt, metal;
    private Context mCtx;
    private boolean isLandscape, isPinching, lighting, moveTable;
    private float ratio;
    private TransformationParams param;
    private float prevX, prevY, prevDist;
    private int scrWidth, scrHeight;
    
    private final static float lightAmb[] = {0.4f, 0.4f, 0.4f, 1f};
    private final static float lightDif[] = {.5f, .5f, .5f, 1f};
    private final static float lightPos[] = {0f, -.5f, 5.0f, 1f};
    private final static float lightDir[] = {0f, .5f, -1f, 0f};
    private final static float materialAmb[] = {0.4f, 0.4f, 0.48f, .5f};
    private final static float materialSpe[] = {0.2f, .2f, .2f, .5f};
    private boolean anim;
	private boolean drawCake;
    
    public GLRenderer(Context parent, TransformationParams p)
    {
        mCtx = parent;
        anim = true;
        drawCake = false;
        moveTable = false;
        chalice = new Chalice(parent, 10.0);
        cake = new Cake(parent, 10.0);
        cubes = new Cube[4];
        for(int i = 0; i < 4; i++){
        	cubes[i] = new Cube(parent);
        	cubes[i].rotateZ(i*90f);
        	cubes[i].localTranslate(4f, 0f, 0f);
        }
        
        table = new Table(parent);

        /* initialize transformation variables */
        param = p;
        lighting = true;
    }
    
    @Override
    public synchronized void onDrawFrame(GL10 gl) { 
        // display function
        gl.glClearColor (0, 0, 0, 1f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL10.GL_COLOR_MATERIAL);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, param.eyeX, param.eyeY, param.eyeZ, /* eye */ 
                param.coa[0], param.coa[1], param.coa[2], /* focal point */
                0, 0, 1f); /* up */
        gl.glColor4f(1.0f, 1.0f, 1.0f, .1f);
        gl.glPushMatrix();
        gl.glTranslatef(param.litePos[0], param.litePos[1], param.litePos[2]);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION, lightDir, 0);
        gl.glPopMatrix();
        
        if (lighting)
        	gl.glEnable(GL10.GL_LIGHTING);
        else
        	gl.glDisable (GL10.GL_LIGHTING);
        
        //draw the table
        wood.bind();
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        if(moveTable){
        	table.rotateX(param.roll_x*100);
        	table.rotateY(param.roll_y*100);
        }
		table.draw(anim);
		wood.unbind();
        
		if(drawCake){
			gl.glPushMatrix();
			//brown
			gl.glColor4f(150/255f, 75/255f, 0/255f, 1f);
			gl.glTranslatef(0, 0, 1);
			cakeTxt.bind();
			cake.draw(null);
			cakeTxt.unbind();
			gl.glPopMatrix();
		}
        
		gl.glPushMatrix();
		gl.glTranslatef(param.chaliceTrX, param.chaliceTrY, 0.5f);//move the chalice
		gl.glScalef(chaliceSize, chaliceSize, chaliceSize);
        metal.bind();
        //gold metal
        gl.glColor4f(1f, 215/255f, 0f, 1);
        gl.glMultMatrixf(table.getCF(), 0);//make the chalice move with the table
		chalice.draw();
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        //greyish metal
        gl.glColor4f(.4f, .4f, .4f, 1f);
        gl.glMultMatrixf(table.getCF(), 0);//make the cubes move with the table
        for(int i = 0; i < 4; i++){
        	cubes[i].draw(anim);
        }
        
        gl.glPopMatrix();
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
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_CUTOFF, 20);
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_EXPONENT, 1);
        
        gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, lightAmb,0);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT_AND_DIFFUSE, materialAmb, 0);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, materialSpe, 0);
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, .1f);
        gl.glEnable(GL10.GL_COLOR_MATERIAL);
        
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        //init our textures
        wood = new Texture (mCtx, R.drawable.wood);
        cakeTxt = new Texture (mCtx, R.drawable.cake);
        metal = new Texture (mCtx, R.drawable.metal);
    }
 
    //example slightly modified to move the chalice instead of a ball
    public void doSwipe(MotionEvent ev, int which)
    {
        switch (ev.getAction() & MotionEvent.ACTION_MASK)
        {
        case MotionEvent.ACTION_DOWN:
            prevX = ev.getX();
            prevY = ev.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            float tx = 2 * (isLandscape ? ratio : 1.0f) * (ev.getX() - prevX) / scrWidth;
            float ty = 2 * (isLandscape ? 1.0f : ratio) * (prevY - ev.getY()) / scrHeight;
            switch (which) {
            case R.id.movelight:
                param.litePos[0] += tx;
                param.litePos[1] += ty;
                break;
            case R.id.movetable:
                param.roll_x = tx;
                param.roll_y = ty;
                break;
            case R.id.movechalice:
                param.chaliceTrX += tx; 
                param.chaliceTrY += ty;
                break;
            case R.id.moveeye:
                param.eyeX += tx;
                param.eyeY += ty;
                break;
            case R.id.movefocus:
                param.coa[0] += tx;
                param.coa[1] += ty;
            }
            prevX = ev.getX();
            prevY = ev.getY();
            break;
        case MotionEvent.ACTION_UP:
        }
    }
    
    //example from class
    public void doPinch (MotionEvent ev, int which)
    {
        /* two fingers are pressed */
        float dx, dy, currDist;

        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_POINTER_DOWN:
            switch (which) {
            case R.id.moveeye:
            case R.id.movelight:
            case R.id.movefocus:
                prevX = ev.getX(0);
                prevY = ev.getY(0);
            }
            break;
        case MotionEvent.ACTION_POINTER_UP:
            isPinching = false;
            break;
        case MotionEvent.ACTION_MOVE:
            float tx = (ev.getX(0) - prevX) / scrWidth;
            float ty = (prevY - ev.getY(0)) / scrHeight;
            switch (which) {
            case R.id.moveeye:
                param.eyeZ += 2 * (tx + ty);
                break;
            case R.id.movefocus:
                param.coa[2] += 2 * (tx + ty);
                break;
            case R.id.movelight:
                param.litePos[2] += 2 * (tx + ty);
                break;
            }
            prevX = ev.getX(0);
            prevY = ev.getY(0);
            break;
        default:
            break;
        }    	
    }

    private long tstamp;
    
    //example modified to tilt the table
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
            param.roll_x = 5 * gravity_x * dt * dt*dt;//rotates the table
            param.roll_y = 5 * gravity_y * dt * dt*dt;
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

	public void setMoveTable(boolean isChecked) {
		moveTable = isChecked;
		
	}
	public void setDrawCake(boolean draw){
		this.drawCake = draw;
	}
}

