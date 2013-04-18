package edu.gvsu.cis.proj3;
import static android.opengl.GLES11.*;
import android.content.Context;
import android.opengl.Matrix;
import android.util.Log;
import edu.gvsu.cis.proj2.SceneObj;

public class Ball extends SceneObj{
	private float  x, y, prevX, prevY;
	private float rollX, rollY;
	private MeshObject meshObj;
	public static int bounds = 10;
	public Ball(Context con){
		Matrix.setIdentityM(cf, 0);
		meshObj = new MeshObject(con, "sphere.off", false);
		rollX = rollY =x = y = prevX = prevY = 0;
		size = 1.0;
	}
	
	//This makes the ball roll naturally, adapted from Dr. Dulimarta's code
	public void render(){
		//if(Math.abs(x-prevX)>rollX){
		//	rollX = 0;
		//	rollY = 0;
		//}
		/* (rollX, rollY) is the direction where the sphere is supposed to roll */
        double rollDist = Math.sqrt(rollX * rollX + 
        		rollY * rollY);
        double rollAng = (rollDist * 180.0) / (Math.PI * size);
        
        if (rollDist > 1E-6) { /* avoid divide by zero */
			glPushMatrix();
			/*
			 * the following the new roll rotation (expressed in the fixed
			 * global coordinate) is superimposed on the current sphere (total)
			 * rotation. This technique was based on the snippet of OpenGL code
			 * posted at
			 * http://www.gamedev.net/topic/190307-solved-code-for-a-rolling-ball
			 * posted on (Nov 12, 2003).
			 */
			glLoadIdentity();
			/* axis of rotation is perpendicular to the roll direction */
			glRotatef((float) rollAng, -rollY / (float) rollDist,
					rollX / (float) rollDist, 0);
			glMultMatrixf(cf, 0);
			glGetFloatv(GL_MODELVIEW_MATRIX, cf, 0);
			glPopMatrix();
			//Log.d("", "Ball (" + x + "," + y + ")"+
	        //    " rollx=" + rollX + " rolly=" +rollY);
        }

        glPushMatrix();
		glTranslatef(x, y, 0.5f);
		glMultMatrixf(cf, 0);
		glScalef((float)size, (float)size, (float)size);
	}


	@Override
	public void draw(Object... objects) {
		render();
		meshObj.draw(null);
        glPopMatrix();
	}
	public void roll(float distX, float distY) {
		rollX = distX;
		rollY = distY;
		
		if((x < -bounds && rollX < 0)|| (x > bounds && rollX >0)){
			rollX = 0;
		}
		if((y < -bounds && rollY < 0) || (y > bounds && rollY >0)){
			rollY = 0;
		}
		x+= rollX;
		y+= rollY;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public double getSize(){
		return this.size;
	}
}
