package edu.gvsu.cis.proj1;

import android.content.Context;
import android.opengl.Matrix;
import edu.gvsu.cis.proj2.SceneObj;
import edu.gvsu.cis.proj3.MeshObject;
import static android.opengl.GLES11.*;

/**
 * This Object is a companion cube with a metallic texture.
 * @author Cam
 *
 */
public class Cube extends SceneObj {
	private MeshObject meshObj;
	private float x,y,width,length;
	public Cube(Context con){
		Matrix.setIdentityM(cf, 0);
		meshObj = new MeshObject(con,"cube.obj",true);
		size = .5;
		width = .2f;
		length = 2f;
	}
	@Override
	public void draw(Object... objects) {
		boolean spin = false;
		if(objects.length==1){
			spin = (Boolean)objects[0];
		}
		if(spin){
			this.rotateZ(.5f);
		}
		glPushMatrix();
		glTranslatef(0,0,0f);
		glScalef(width,length,(float)size);
		glMultMatrixf(cf,0);
		meshObj.draw(null);
		glPopMatrix();
	}
	
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

}
