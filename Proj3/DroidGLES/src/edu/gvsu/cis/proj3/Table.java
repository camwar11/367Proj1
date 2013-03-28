package edu.gvsu.cis.proj3;

import android.content.Context;
import android.opengl.Matrix;
import edu.gvsu.cis.proj2.SceneObj;
import static android.opengl.GLES11.*;

/**
 * Wooden table that can rotate.
 * @author Cam
 *
 */
public class Table extends SceneObj {
	private MeshObject meshObj;
	
	public Table(Context con){
		Matrix.setIdentityM(cf, 0);
		meshObj = new MeshObject(con, "table.off", false);
	}
	@Override
	public void draw(Object... objects) {
		boolean rotate = false;
		if(objects.length==1){
			rotate = (Boolean)objects[0];
		}
		if(rotate)
			this.rotateZ(2f);//rotate if we should
		float[] mulMat = new float[16];
		Matrix.scaleM(mulMat, 0, cf, 0, 7f, 7f, 7f);
		Matrix.translateM(mulMat, 0, -.475f, .5f, -.5f);
		Matrix.rotateM(mulMat, 0, 90f, 1f, 0f, 0f);
		glPushMatrix();
		glMultMatrixf(mulMat,0);
		meshObj.draw(null);
		glPopMatrix();
	}

}
