/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gvsu.cis.proj2;

import javax.microedition.khronos.opengles.GL11;
import static android.opengl.GLES11.*;
import edu.gvsu.cis.proj3.MeshObject;

import android.content.Context;
import android.opengl.*;

/**
 * Chalice that will spin
 * @author warnecam
 */
public class Chalice extends SceneObj{
	private MeshObject meshObj;
    public Chalice(Context context, double size){//, Triple<Float> color){
        this.size = size;
        Matrix.setIdentityM(cf, 0);
        Matrix.scaleM(cf, 0, 2f, 2f, 2f);
        
        //this.localTranslate(0f, -.75f, 0f);
        meshObj = new MeshObject(context, "chalice.off",false);
    }
    
    @Override
    public void draw(Object... objects){
    	float[] multMat = new float[16];
    	Matrix.rotateM(multMat, 0, cf, 0, 90, 1f, 0, 0);
    	Matrix.translateM(multMat, 0, 0f, -.5f, 0f);
        glPushMatrix();
        //glMultMatrixf(Test.cyl.getCF(), 0);
        glMultMatrixf(multMat, 0);
        //glColor3f(color.R(), color.G(), color.B());
        meshObj.draw(null);
        glPopMatrix();
    }
    
    
    public static void initMaterial(GL11 gl){
        /* define material properties */
        float[] material_ambient = new float[]{0.25f, 0.25f, 0.25f};
        float[] material_diffuse = new float[]{0.90f, 0.90f, 0.90f, 0};
        float[] material_specular = new float[]{0.90f, 0.90f, 0.90f, 0};
        float material_shininess = 25.0f;

        /* load material properties */
        gl.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, material_ambient, 0);
        gl.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, material_diffuse, 0);
        gl.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, material_specular, 0);
        gl.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, material_shininess);
    }
    
}
