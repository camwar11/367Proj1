package edu.gvsu.cis.proj1;

import android.content.Context;
import android.graphics.Color;
import android.opengl.Matrix;
import edu.gvsu.cis.proj2.SceneObj;
import edu.gvsu.cis.proj3.Ball;
import edu.gvsu.cis.proj3.MeshObject;
import static android.opengl.GLES11.*;

/**
 * This Object is a companion cube with a metallic texture.
 * @author Cam
 *
 */
public class Cube extends SceneObj {
	private static MeshObject meshObj = null;
	private float x,y,width,length;
	private static float[][] colors = {{.7f,0f,0f},{0f,.7f,0f}};//red, green
	private int color, score;
	private long oldTime, changeTime;
	public Cube(Context con){
		Matrix.setIdentityM(cf, 0);
		if(meshObj == null) //only load the object once
			meshObj = new MeshObject(con,"cube.obj",true);
		size = .5;
		width = .5f;
		length = .5f;
		move();
	}
	@Override
	public void draw(Object... objects) {
		if(System.currentTimeMillis() >= this.changeTime){
			move();
		}
		glPushMatrix();
		glColor4f(colors[color][0], colors[color][1], colors[color][2], 1f);
		glTranslatef(x,y,0f);
		glScalef(width,length,(float)size);
		glMultMatrixf(cf,0);
		meshObj.draw(null);
		glPopMatrix();
	}
	/**
	 * Checks if this cube intersects another object (square to estimate a circle)
	 * @param x the x coord of the other obj
	 * @param y the y coord of the other obj
	 * @param size the size of the other obj
	 * @return true if they intersect, else false
	 */
	public boolean intersects(float x, float y, float size){
		return checkX(x,size) && checkY(y,size);
	}
	private boolean checkX(float x, float size){
		if(x < this.x +width && x > this.x){
			return true;
		}
		if(x + size > this.x && x + size < this.x + width){
			return true;
		}
		return false;
	}
	private boolean checkY(float y, float size){
		if(y < this.y + length && y > this.y){
			return true;
		}
		if(y + size > this.x && y + size < this.y + length){
			return true;
		}
		return false;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * Randomly moves the cube and assigns it a new color
	 */
	public int move() {
		int oldScore = this.score;
		this.oldTime = System.currentTimeMillis();
		this.changeTime = this.oldTime + 1000 * 8;//8 secs
		color = (int) Math.round(Math.random());//0 or 1, ie red or green
		if(color == 0 ){
			score = -5;
		}else{
			score = 10;
		}
		x = (float)Math.random() * Ball.bounds;
		y = (float)Math.random() * Ball.bounds;
		if(Math.round(Math.random()) == 0){
			x = -x;
		}
		if(Math.round(Math.random()) == 0){
			y = -y;
		}
		return oldScore;
	}
}
