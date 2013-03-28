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
	
	public Cube(Context con){
		Matrix.setIdentityM(cf, 0);
		meshObj = new MeshObject(con,"cube.obj",true);
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
		glScalef(.5f,.5f,.5f);
		glMultMatrixf(cf,0);
		meshObj.draw(null);
		glPopMatrix();
	}

}
